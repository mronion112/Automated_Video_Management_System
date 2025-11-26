package org.example.Module.Repsitory.Data.Video;

import com.github.kokorin.jaffree.nut.StreamHeader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.Module.Entity.Data.Story.WebStoryEntity;
import org.example.Module.Entity.Data.Video.WebVideoEntity;
import org.example.Module.Repsitory.Data.WebDataRespository;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.time.Duration;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebVideoRepository extends WebDataRespository {
    private HashMap<String, WebVideoEntity> listWebVideo = new HashMap<>();

    //Remember to add .exe
    private static String ytDlpPath = "Tool_Helper" + File.separator + "ytDlp";
    public static File ffmpegPath = new File("Tool_Helper" + File.separator + "ffmpeg.exe");



    //CURD : Create , Update, Read, Delte

    public void addNewListWebVideo(String nameVideo, WebVideoEntity webVideoEntity){
        listWebVideo.put(nameVideo, webVideoEntity);
    }

    public HashMap<String, WebVideoEntity> getListWebVideo(){
        return listWebVideo;
    }
    public WebVideoEntity getWebVideoByNameVideo(String nameVideo) throws IOException
    {
        return listWebVideo.get(nameVideo);

    }

    public void updateWebVideo(String nameVideo, WebVideoEntity webVideoEntity) throws IOException
    {
        listWebVideo.replace(nameVideo, webVideoEntity);

    }
    public void deleteWebVideo() throws IOException
    {
        listWebVideo.clear();
    }
    public void deleteWebVideoByName(String nameVideo) throws IOException
    {
        listWebVideo.remove(nameVideo);
    }





    public void killChromeWithProfile(String profilePath) {
        try {
            String escapedPath = profilePath.replace("\\", "\\\\");
            String command = "wmic process where \"CommandLine like '%" + escapedPath + "%' and Name='chrome.exe'\" call terminate";
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            System.out.println("Đã đóng Chrome dùng profile: " + profilePath);
        } catch (Exception e) {
            System.out.println("Không thể đóng Chrome của profile này: " + e.getMessage());
        }
    }


    public HashMap<String, WebVideoEntity> scrappingVideoChannel(String urlChannel){

        HashMap<String, WebVideoEntity> scrappingVideoData = new HashMap<>();

        WebDriverManager.chromedriver()
                .cachePath("driver_cache") // thư mục lưu cache
                .setup();

        String currentDir = System.getProperty("user.dir");
        String profilePath = currentDir + File.separator + "Onion_profile";

        killChromeWithProfile(profilePath);


        File profileDir = new File(profilePath);
        if (!profileDir.exists() && profileDir.mkdirs()) {
            System.out.println(" Đã tạo thư mục profile mới tại: " + profilePath);
        }


        ChromeOptions options = new ChromeOptions();
//            options.addArguments("--headless");
        options.addArguments("--log-level=3"); // ERROR only
        options.addArguments("--silent");
        options.addArguments("--disable-logging");
        options.addArguments("user-data-dir=" + profilePath);
        options.addArguments("profile-directory=Default");
        options.addArguments("--remote-debugging-port=9222");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36");

        Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
        Logger.getLogger("org.openqa.selenium.remote").setLevel(Level.OFF);
        Logger.getLogger("org.openqa.selenium.chromium").setLevel(Level.OFF);
        Logger.getLogger("io.netty").setLevel(Level.OFF);


        ChromeDriverService service = new ChromeDriverService.Builder()
                .withSilent(true)
                .withLogOutput(new OutputStream() { @Override public void write(int b) {} })
                .build();



        ChromeDriver driver = null;





        try {
            driver = new ChromeDriver(service, options);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            driver.get(urlChannel);

            Thread.sleep(3000);

            boolean loaded = false;
            Thread.sleep(1000);
            try {
                WebElement firstVideo = null;
                WebElement close_button = null;
                int maxRefresh = 10;
                int refreshCount = 0;

                while (refreshCount < maxRefresh) {
                    try {
                        firstVideo = new WebDriverWait(driver, Duration.ofSeconds(1))
                                .until(ExpectedConditions.visibilityOfElementLocated(
                                        By.cssSelector("div.bili-video-card__title a")
                                ));
                        break;
                    } catch (TimeoutException e) {
                        refreshCount++;
                        if (refreshCount < maxRefresh) {
                            close_button = new WebDriverWait(driver, Duration.ofSeconds(2))
                                    .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.bili-mini-close-icon")));
                            close_button.click();
                            Thread.sleep(1000);
                            driver.navigate().refresh();
                        }
                    }
                }
                if(refreshCount == maxRefresh){
                    System.out.println("Can't access bili video channel ");
                    return new HashMap<>();
                }

                if (firstVideo == null) {
                    System.out.println("Không tìm thấy phần tử sau " + maxRefresh + " lần refresh.");
                }



                boolean hasNext = true;
                int PageScan = 0;

                // HashMap<String, WebVideoEntity> listWebVideo

                do {
                    // 🔹 Lấy danh sách video hiện tại
                    List<WebElement> videoCards = driver.findElements(By.cssSelector(".upload-video-card.grid-mode"));


                    System.out.println("Tìm thấy tổng cộng: " + videoCards.size() + " video.");

                    for (WebElement card : videoCards) {

                        try {
                            // --- BẮT ĐẦU TÌM KIẾM TƯƠNG ĐỐI (RELATIVE FIND) TỪ THẺ CARD ---

                            // 1. Tìm thẻ Link (thẻ a) và Ảnh (thẻ img)
                            // Chúng ta phải đi sâu vào cấu trúc con: .bili-video-card -> .bili-cover-card
                            WebElement linkElement = card.findElement(By.cssSelector(".bili-video-card__title a")); // <--- ĐÃ SỬA

                            WebElement imgElement = card.findElement(By.cssSelector(".bili-cover-card__thumbnail img"));

                            String videoUrl = linkElement.getAttribute("href");
                            String thumbnailUrl = imgElement.getAttribute("src");

                            String title = linkElement.getText();

                            // 2. Tìm các chỉ số thống kê (View, Comment, Thời lượng)
                            // Lưu ý: .bili-cover-card__stats chứa các .bili-cover-card__stat
                                List<WebElement> stats = card.findElements(By.cssSelector(".bili-cover-card__stat span"));

                            // Xử lý logic gán dữ liệu dựa trên thứ tự xuất hiện
                            String viewCount = "";;
                            String commentCount = "";
                            String duration = "";
                            if (stats.size() >= 3) {
                                viewCount = stats.get(0).getText();      // Dòng 1: View (Ví dụ: 4.0万)
                                    viewCount.replaceAll("万", "k");

                                commentCount = stats.get(1).getText();   // Dòng 2: Danmaku/Comment (Ví dụ: 136)
                                    viewCount.replaceAll("万", "k");

                                duration = stats.get(2).getText();       // Dòng 3: Thời lượng (Ví dụ: 14:29)
                            } else {
                                // Log warning nếu cấu trúc lạ
                                System.out.println("Video này thiếu thông tin thống kê: ");
                            }

                            WebElement date = card.findElement(By.cssSelector(".bili-video-card__subtitle"));
                            String dateText = date.getText();

                            String[] dateTime = dateText.split("-");


                            if(dateTime.length == 0){
                                dateText = LocalDate.now().toString();
                            }
                            if(dateTime.length == 2){
                                dateText = LocalDate.now().getYear() + "-" + dateText;

                            }



                            // Thêm vào danh sách kết quả
                            listWebVideo.put(title, new WebVideoEntity(videoUrl, thumbnailUrl, title, duration, viewCount, commentCount, dateText ));

                        } catch (Exception e) {
                            // Nếu 1 video bị lỗi (do chưa load xong hoặc cấu trúc khác), in lỗi và bỏ qua, chạy video tiếp theo
                            System.out.println("Lỗi khi cào video tại index " + videoCards.indexOf(card) + ": " + e.getMessage());
                        }
                    }
                    PageScan++;

                    ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
                    Thread.sleep(1000);

                    try {
                        // Tìm nút có text = "下一页"
                        WebElement nextButton = driver.findElement(By.xpath("//button[contains(text(),'下一页')]"));

                        // Kiểm tra xem nút còn hoạt động không
                        if (nextButton.isEnabled() && nextButton.getAttribute("disabled") == null) {
                            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextButton);
                            Thread.sleep(500);
                            nextButton.click();
                            System.out.println("Chuyển sang trang tiếp theo...");
                            Thread.sleep(2000);
                        } else {
                            System.out.println("Hết trang, dừng lại.");
                            hasNext = false;
                        }

                    } catch (NoSuchElementException e) {
                        System.out.println("❌ Không tìm thấy nút 下一页 — dừng lại.");
                        hasNext = false;
                    }

                } while (hasNext);
                System.out.println("Số trang đã quét : " + PageScan);
                System.out.println("Tổng số video quét được: " + listWebVideo.size());


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (driver != null) {
                    driver.quit();
                }
            }


            return listWebVideo;



        } catch (Exception e) {
            e.printStackTrace();
        }
        return listWebVideo;
    }





}



