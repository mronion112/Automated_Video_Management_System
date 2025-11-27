package org.example.Module.Repsitory.Data.Video;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.Module.Entity.Data.Video.LocalVideoEntity;
import org.example.Module.Entity.Data.Video.WebVideoEntity;
import org.example.Module.Repsitory.Data.WebDataRespository;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebVideoRepository extends WebDataRespository {

    private static final String VIDEO_CARD_SELECTOR = ".upload-video-card.grid-mode";
    private static final String TITLE_LINK_SELECTOR = ".bili-video-card__title a";
    private static final String THUMBNAIL_SELECTOR = ".bili-cover-card__thumbnail img";
    private static final String STATS_SELECTOR = ".bili-cover-card__stat span";
    private static final String DATE_SELECTOR = ".bili-video-card__subtitle";
    private static final String CLOSE_ICON_SELECTOR = "div.bili-mini-close-icon";
    private static final String NEXT_BUTTON_XPATH = "//button[contains(text(),'下一页')]";

    private final String YTDLP_PATH = "Tool_Helper" + File.separator + "yt-dlp";
    private final File FFMPEG_PATH = new File("Tool_Helper" + File.separator + "ffmpeg");
    private final String ARIA2C_PATH = "Tool_Helper" + File.separator + "aria2c";


    private final Gson gson = new Gson();
    private final Type TYPE_WEB_VIDEO = new TypeToken<HashMap<String, WebVideoEntity>>() {}.getType();
    private final Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().create();

    private File videoStorageFolder;
    private final HashMap<String, WebVideoEntity> listWebVideo = new HashMap<>();
    private HashMap<String, LocalVideoEntity> listLocalVideo;

    public WebVideoRepository(File videoStorage) {
        this.videoStorageFolder = videoStorage;
    }



    public WebVideoEntity downloadData(String nameVideo, HashMap<String, WebVideoEntity> listVideoDownloaded, HashMap<String, WebVideoEntity> processJsonData, int idFolder) throws IOException, InterruptedException {


        String folderDir = createVideoFolder(idFolder);
        System.out.println();

        System.out.println(idFolder+"/"+processJsonData.size());

        WebVideoEntity videoDownload = processJsonData.get(nameVideo);

        String urlVideo = "";
        if(videoDownload == null){
            System.out.println("NameVideo not avalable in procesJsonData");
        }
        else{
            urlVideo = videoDownload.getUrlData();
        }

        System.out.println("Download video " + nameVideo);
        downLoadVideoFromUrl(folderDir, idFolder, urlVideo);

        downloadThumbnailVideoFromUrl(folderDir, idFolder, urlVideo);


        WebVideoEntity newVideoDownLoad = processJsonData.get(nameVideo);
        return newVideoDownLoad;

    }


    public String createVideoFolder(int idFolder) throws FileNotFoundException {
        File folder = new File(videoStorageFolder, String.valueOf(idFolder));
        if (!folder.exists()) {
            boolean created = folder.mkdirs();
            System.out.println("Folder created: " + created);
        } else {
            System.out.println("Folder already exist " + folder.getAbsolutePath());
        }
        return folder.getAbsolutePath();
    }


    public void downLoadVideoFromUrl(String folderDir, int idFolder, String videoUrl) throws InterruptedException, IOException {
        if (!FFMPEG_PATH.exists()) {
            System.err.println("Not found ffmpeg: " + FFMPEG_PATH.getAbsolutePath());
            return;
        }

        String videoId = String.valueOf(idFolder);
        String outputTemplate = folderDir + File.separator + videoId + ".mp4";

        ProcessBuilder pb = new ProcessBuilder(
                YTDLP_PATH,
                "--no-warnings",
                "--progress",
//                "--no-progress",
//                "--quiet",
                "--socket-timeout", "600    ",
                "--external-downloader", ARIA2C_PATH,
                "--external-downloader-args", "-x 16 -s 16 -k 1M",
                "--postprocessor-args", "-loglevel quiet",
                "--ffmpeg-location", FFMPEG_PATH.getAbsolutePath(),
                "-f", "bestvideo+bestaudio/best",
                "-o", outputTemplate,
                "--newline",
                videoUrl
        );

        pb.redirectErrorStream(true);
        Process process = pb.start();


        Pattern aria2cPattern = Pattern.compile(
                "\\[#.+?\\s+(\\S+)/\\S+\\((\\d+)%\\)\\s+CN:\\d+\\s+DL:(\\S+)(?:\\s+ETA:(\\S+))?\\]"
        );

        Pattern ytDlpPattern = Pattern.compile(
                "(\\d+(?:\\.\\d{1,2})?)%\\s*\\|\\s*(\\S+)\\s*\\|\\s*(\\S+)/s\\s*\\|\\s*ETA:\\s*(\\S+)"
        );

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                Matcher m_aria = aria2cPattern.matcher(line);
                Matcher m_ytdlp = ytDlpPattern.matcher(line);

                String percentStr = null;
                String speed = "0B";
                String eta = "--:--";

                boolean isProgress = false;


                if (m_aria.find() || m_ytdlp.find()) {
                    if (percentStr != null) {
                        printProgress(percentStr, eta, speed);
                    }
                } else {
                    String cleanLine = line.trim();

                    if (cleanLine.contains("[Merger]") || cleanLine.contains("[download]") || cleanLine.contains("Deleting original file")) {
                        System.out.print("\r                                                                      \r");
                    }

                }
            }
        }

        int exitCode = process.waitFor();
        System.out.println();

        if (exitCode == 0) {
            System.out.println("Video Download Successful");
        } else {
            System.err.println("Lỗi tải video: Exit code " + exitCode);
        }
    }

    private void printProgress(String percentStr, String eta, String speed) {
        try {
            int percent = (int) Double.parseDouble(percentStr.replace(",", "."));

            String displayLine = String.format(
                    "\r%3d%% | Tốc độ: %s/s | Còn: %-6s ",
                    percent, speed, eta
            );

            System.out.print(displayLine);
            System.out.flush();

        } catch (NumberFormatException ignored) {
        }
    }

    public void downloadThumbnailVideoFromUrl(String folderDir, int idFolder, String videoUrl) throws IOException, InterruptedException {

        String Thumbnail_Path = folderDir + File.separator + idFolder;
        ProcessBuilder pb = new ProcessBuilder(
                YTDLP_PATH,
                "--quiet",
                "--no-warnings",
//                "--print", "none",
                "--progress",
                "--socket-timeout", "600",
                "--skip-download",         // Không tải video
                "--write-thumbnail",       // Chỉ tải thumbnail
                "--convert-thumbnails", "jpg",
                "-o", Thumbnail_Path,  // Đường dẫn file
                videoUrl                   // URL video thumnail
        );

        pb.redirectErrorStream(true);
        Process process = pb.start();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        int exitCode = process.waitFor();
        if (exitCode == 0) {
            System.out.println("Đã tải thumbnail thành công cho video: ");
        } else {
            System.out.println("Lỗi khi tải thumbnail (exit code " + exitCode + ")");
        }

    }



    private ChromeDriver createChromeDriver(String profilePath, ChromeDriverService service, ChromeOptions options) {
        WebDriverManager.chromedriver().cachePath("driver_cache").setup();

        String currentDir = System.getProperty("user.dir");
        String fullProfilePath = currentDir + File.separator + profilePath;

        killChromeWithProfile(fullProfilePath);

        File profileDir = new File(fullProfilePath);
        if (!profileDir.exists() && profileDir.mkdirs()) {
            System.out.println(" Đã tạo thư mục profile mới tại: " + fullProfilePath);
        }

        options.addArguments("--log-level=3");
        options.addArguments("user-data-dir=" + fullProfilePath);
        options.addArguments("--remote-allow-origins=*");

        Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);

        return new ChromeDriver(service, options);
    }

    private Actions createActions(WebDriver driver) {
        return new Actions(driver);
    }


    public HashMap<String, WebVideoEntity> scrappingVideoChannel(String urlChannel) {

        ChromeOptions options = new ChromeOptions();
        ChromeDriverService service = new ChromeDriverService.Builder()
                .withSilent(true)
                .withLogOutput(new OutputStream() { @Override public void write(int b) {} })
                .build();

        ChromeDriver driver = createChromeDriver("Onion_profile", service, options);

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            driver.get(urlChannel);
            Thread.sleep(3000);

            if (!waitForInitialLoad(driver, 10)) {
                System.out.println("Can't connect to Web");
                return new HashMap<>();
            }

            scrapeVideoData(driver);

            return listWebVideo;

        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    private boolean waitForInitialLoad(WebDriver driver, int maxRefresh) throws InterruptedException {
        int refreshCount = 0;
        while (refreshCount < maxRefresh) {
            try {
                new WebDriverWait(driver, Duration.ofSeconds(3))
                        .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(TITLE_LINK_SELECTOR)));
                return true;
            } catch (TimeoutException e) {
                refreshCount++;
                if (refreshCount < maxRefresh) {
                    try {
                        WebElement closeButton = new WebDriverWait(driver, Duration.ofSeconds(1))
                                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(CLOSE_ICON_SELECTOR)));
                        closeButton.click();
                    } catch (TimeoutException | NoSuchElementException ignored) {
                    }
                    Thread.sleep(1000);
                    driver.navigate().refresh();
                }
            }
        }
        return false;
    }

    private void scrapeVideoData(ChromeDriver driver) throws InterruptedException {
        int pageScan = 0;
        boolean hasNext = true;

        JavascriptExecutor js = (JavascriptExecutor) driver;

        do {
            List<WebElement> videoCards = driver.findElements(By.cssSelector(VIDEO_CARD_SELECTOR));
            System.out.println("--- Trang " + (pageScan + 1) + " --- Tìm thấy: " + videoCards.size() + " video.");

            for (WebElement card : videoCards) {
                try {
                    WebElement titleDiv = card.findElement(By.cssSelector(".bili-video-card__title"));
                    WebElement linkElement = titleDiv.findElement(By.tagName("a"));
                    WebElement imgElement = card.findElement(By.cssSelector(THUMBNAIL_SELECTOR));

                    String videoUrl = linkElement.getAttribute("href");
                    String thumbnailUrl = imgElement.getAttribute("src");

                    String title = titleDiv.getAttribute("title");
                    if (title == null || title.isEmpty()) {
                        title = linkElement.getText();
                    }

                    List<WebElement> stats = card.findElements(By.cssSelector(STATS_SELECTOR));
                    String[] statsData = processStats(stats);
                    String viewCount = statsData[0];
                    String commentCount = statsData[1];
                    String duration = statsData[2];

                    WebElement dateElement = card.findElement(By.cssSelector(DATE_SELECTOR));
                    String dateText = formatDate(dateElement.getText());

                    listWebVideo.put(title, new WebVideoEntity(videoUrl, thumbnailUrl, title, duration, viewCount, commentCount, dateText));

                } catch (Exception e) {
                    System.out.println("Lỗi khi cào một video (Bỏ qua): " + e.getMessage());
                }
            }
            pageScan++;

            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(1000);

            hasNext = clickNextPage(driver, js);

        } while (hasNext);

        System.out.println("Tổng số trang đã quét : " + pageScan);
        System.out.println("Tổng số video quét được: " + listWebVideo.size());
    }

    private String[] processStats(List<WebElement> stats) {
        String viewCount = "";
        String commentCount = "";
        String duration = "";

        if (stats.size() >= 3) {
            viewCount = stats.get(0).getText().replaceAll("万", "k");
            commentCount = stats.get(1).getText().replaceAll("万", "k");
            duration = stats.get(2).getText();
        }
        return new String[]{viewCount, commentCount, duration};
    }

    private String formatDate(String rawDate) {
        String[] dateTime = rawDate.split("-");

        if (dateTime.length == 2) {
            return LocalDate.now().getYear() + "-" + rawDate;
        } else if (dateTime.length == 3) {
            return rawDate;
        } else {
            return LocalDate.now().toString();
        }
    }

    private boolean clickNextPage(WebDriver driver, JavascriptExecutor js) throws InterruptedException {
        try {
            WebElement nextButton = driver.findElement(By.xpath(NEXT_BUTTON_XPATH));

            if (nextButton.isEnabled() && nextButton.getAttribute("disabled") == null) {
                js.executeScript("arguments[0].scrollIntoView(true);", nextButton);
                Thread.sleep(500);
                nextButton.click();
                System.out.println("Chuyển sang trang tiếp theo...");
                Thread.sleep(2000);
                return true;
            } else {
                System.out.println("Hết trang, dừng lại.");
                return false;
            }

        } catch (NoSuchElementException e) {
            System.out.println("Không tìm thấy nút Next Page — dừng lại.");
            return false;
        }
    }



    public void wirteProcessJson(File processJson, HashMap<String, WebVideoEntity> listVideoDownloaded) throws IOException {

        try (FileWriter writer = new FileWriter(processJson, false)) {
            String dataProcess = gsonBuilder.toJson(listVideoDownloaded);
            writer.write(dataProcess);
            System.out.println("Đã ghi dữ liệu JSON thành công.");
        }
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


    // --- CURD & Getters/Setters (Giữ nguyên) ---

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


    public void setListWebVideo(HashMap<String, WebVideoEntity> listWebVideo) {
        this.listWebVideo.clear();
        this.listWebVideo.putAll(listWebVideo);
    }

    public HashMap<String, LocalVideoEntity> getListLocalVideo() {
        return listLocalVideo;
    }

    public void setListLocalVideo(HashMap<String, LocalVideoEntity> listLocalVideo) {
        this.listLocalVideo = listLocalVideo;
    }

    public File getVideoStorage() {
        return videoStorageFolder;
    }

    public void setVideoStorage(File videoStorage) {
        this.videoStorageFolder = videoStorage;
    }
}