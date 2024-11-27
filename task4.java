import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

public class task4 {

    // Method to fetch exchange rate from the API
    public static double getExchangeRate(String baseCurrency, String targetCurrency) {
        double rate = 0.0;
        try {
            // Construct the URI using URI builder
            URI uri = new URI(
                "https",
                "api.exchangerate.host",
                "/convert",
                "from=" + baseCurrency + "&to=" + targetCurrency,
                null
            );

            // Convert URI to URL
            URL url = uri.toURL();

            // Open HTTP connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Check the response code
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Parse the JSON response to extract the exchange rate
                String jsonResponse = response.toString();
                int rateIndex = jsonResponse.indexOf("\"result\":");
                if (rateIndex != -1) {
                    String rateString = jsonResponse.substring(rateIndex + 9, jsonResponse.indexOf(",", rateIndex));
                    rate = Double.parseDouble(rateString);
                }
            } else {
                System.out.println("Failed to fetch exchange rates. Response code: " + responseCode);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return rate;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get base and target currencies from the user
        System.out.println("Welcome to the Currency Converter!");
        System.out.print("Enter the base currency (e.g., USD, EUR): ");
        String baseCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Enter the target currency (e.g., INR, GBP): ");
        String targetCurrency = scanner.nextLine().toUpperCase();

        // Fetch the exchange rate
        double exchangeRate = getExchangeRate(baseCurrency, targetCurrency);
        if (exchangeRate == 0.0) {
            System.out.println("Could not fetch exchange rates. Please try again later.");
            return;
        }

        // Get the amount to convert
        System.out.print("Enter the amount in " + baseCurrency + ": ");
        double amount = scanner.nextDouble();

        // Perform the currency conversion
        double convertedAmount = amount * exchangeRate;

        // Display the result
        System.out.printf("Converted Amount: %.2f %s\n", convertedAmount, targetCurrency);

        scanner.close();
    }
}
