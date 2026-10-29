
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Api {

    private static final String BASE_URL =
            "https://jsonplaceholder.typicode.com";

    private static final HttpClient client =
            HttpClient.newHttpClient();

    // GET: Fetch all posts
    public static void getAllPosts() throws Exception {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/posts"))
                .GET()
                .build();

        sendRequest(request);
    }

    // GET: Fetch one post
    public static void getPostById(int id) throws Exception {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/posts/" + id))
                .GET()
                .build();

        sendRequest(request);
    }

    // POST: Create a post
    public static void createPost() throws Exception {

        String json = """
                {
                    "title": "Core Java API",
                    "body": "Learning REST API using Java",
                    "userId": 1
                }
                """;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/posts"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        sendRequest(request);
    }

    // PUT: Update the complete post
    public static void updatePost(int id) throws Exception {

        String json = """
                {
                    "id": 1,
                    "title": "Updated Title",
                    "body": "Updated Body",
                    "userId": 1
                }
                """;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/posts/" + id))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();

        sendRequest(request);
    }

    // PATCH: Update selected fields
    public static void patchPost(int id) throws Exception {

        String json = """
                {
                    "title": "Partially Updated Title"
                }
                """;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/posts/" + id))
                .header("Content-Type", "application/json")
                .method("PATCH",
                        HttpRequest.BodyPublishers.ofString(json))
                .build();

        sendRequest(request);
    }

    // DELETE: Delete a post
    public static void deletePost(int id) throws Exception {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/posts/" + id))
                .DELETE()
                .build();

        sendRequest(request);
    }

    // Common method to send requests and display responses
    public static void sendRequest(HttpRequest request)
            throws Exception {

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        System.out.println("\nHTTP Method: "
                + request.method());

        System.out.println("URL: "
                + request.uri());

        System.out.println("Status Code: "
                + response.statusCode());

        System.out.println("Response Body:");
        System.out.println(response.body());

        System.out.println("--------------------------------");
    }

    // Main method
    public static void main(String[] args) {

        try {
            System.out.println("=== CORE JAVA REST API ===");

            // GET all posts
            getAllPosts();

            // GET one post
            getPostById(1);

            // POST new post
            createPost();

            // PUT update post
            updatePost(1);

            // PATCH update post
            patchPost(1);

            // DELETE post
            deletePost(1);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Request interrupted.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
