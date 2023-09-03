package ass1;

import io.javalin.Javalin;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class App {

    /**
     * Main method for the application
     * Starts the Javalin server and defines the routes
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(9000);

        app.get("/", ctx -> {
            try {
                String htmlContent = new String(Files.readAllBytes(Paths.get("index.html")));
                ctx.html(htmlContent);
            } catch (IOException e) {
                ctx.result("Error loading HTML file");
            }
        });

        app.post("/convert", ctx -> {
            try {
                double value = Double.parseDouble(ctx.formParam("value"));
                String fromUnit = ctx.formParam("sunit");
                String toUnit = ctx.formParam("tunit");
                double inMeters = value * getConversionFactor(fromUnit);
                double result = inMeters / getConversionFactor(toUnit);
                ctx.result(Double.toString(result));
            } catch (NumberFormatException e) {
                ctx.result("Invalid input");
            }
        });
    }

    /**
     * Returns the conversion factor for the given unit
     * @param unit The unit to get the conversion factor for
     * @return The conversion factor
     */
    public static double getConversionFactor(String unit) {
        switch (unit) {
            case "in":
                return 0.0254;
            case "ft":
                return 0.3048;
            case "mi":
                return 1609.344;
            case "m":
                return 1.0;
            default:
                throw new IllegalArgumentException("Invalid unit: " + unit);
        }
    }
}