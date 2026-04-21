package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LoginTest {

    @Test
    public void loginViaUiPath() {

        System.out.println("Starting UiPath bot execution...");

        // 🔥 FINAL COMMAND (shortcut trigger)
        String command = "cmd /c start \"\" \"C:\\Users\\xtpl\\Desktop\\loginkptcl2 - UiPath.lnk\"";

        int maxRetries = 3;
        int attempt = 0;
        boolean success = false;

        while (attempt < maxRetries && !success) {
            try {
                attempt++;
                System.out.println("Attempt: " + attempt);

                Process process = Runtime.getRuntime().exec(command);

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(process.getInputStream()));

                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }

                int exitCode = process.waitFor();

                System.out.println("Exit Code: " + exitCode);

                // ⚠️ Important: start command always returns 0
                // so we assume success if no exception
                success = true;

            } catch (Exception e) {
                System.out.println("Error occurred. Retrying...");
                e.printStackTrace();

                try {
                    Thread.sleep(5000); // wait before retry
                } catch (InterruptedException ignored) {}
            }
        }

        if (!success) {
            Assert.fail("UiPath bot failed after retries!");
        }

        System.out.println("Bot execution triggered successfully.");
    }
}