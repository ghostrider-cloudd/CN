import java.io.*; import java.net.*; import java.util.Scanner;
public class SlidingWindowSender {
 public static void main(String[] args) throws Exception {
 ServerSocket serverSocket = new ServerSocket(12345);
 System.out.println("Sender waiting for receiver on port 12345...");
 Socket socket = serverSocket.accept();
 System.out.println("Receiver connected.");
 DataOutputStream out = new DataOutputStream(socket.getOutputStream());
 BufferedReader in = new BufferedReader(new
InputStreamReader(socket.getInputStream()));
 Scanner scanner = new Scanner(System.in);
 int windowSize = 4;
 String[] frames = {"Frame 0", "Frame 1", "Frame 2", "Frame 3", "Frame 4",
"Frame 5", "Frame 6", "Frame 7"};
 int nextFrameToSend = 0, lastAckReceived = -1;
 while (lastAckReceived < frames.length - 1) {
 for (int i = nextFrameToSend; i < Math.min(nextFrameToSend + windowSize,
frames.length); i++) {
 System.out.println("Sending: " + frames[i]);
 out.writeBytes(frames[i] + "\n");
 }
String ackMessage = in.readLine();
if (ackMessage != null && ackMessage.startsWith("ACK")) {
 int ackNumber = Integer.parseInt(ackMessage.substring(4));
 System.out.println("Received ACK for frame " + ackNumber);
 if (ackNumber > lastAckReceived) { lastAckReceived = ackNumber;
nextFrameToSend = ackNumber + 1; }
} else { System.out.println("No ACK or invalid ACK. Resend window..."); }
Thread.sleep(1000); }
System.out.println("All frames sent and acknowledged.");
scanner.close(); socket.close(); serverSocket.close(); }
public class SlidingWindowReceiver {
 public static void main(String[] args) throws Exception {
 Socket socket = new Socket("localhost", 12345);
 System.out.println("Connected to sender.");
 BufferedReader in = new BufferedReader(new
InputStreamReader(socket.getInputStream()));
 DataOutputStream out = new DataOutputStream(socket.getOutputStream());
 int expectedFrame = 0; String receivedFrame;
 while ((receivedFrame = in.readLine()) != null) {
 System.out.println("Received: " + receivedFrame);
 out.writeBytes("ACK " + expectedFrame + "\n");
 System.out.println("Sent ACK for frame " + expectedFrame);
 expectedFrame++;
 }
 System.out.println("Receiver finished."); socket.close();
 }
}
