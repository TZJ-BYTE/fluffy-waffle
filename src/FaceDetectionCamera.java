
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;
import org.opencv.highgui.HighGui;

public class FaceDetectionCamera {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // 创建人脸检测器
        FaceDetector faceDetector = new FaceDetector("F:/ubuntu/opencv/build/etc/haarcascades/haarcascade_frontalface_alt.xml");

        // 打开摄像头
        VideoCapture capture = new VideoCapture(0);
        if (!capture.isOpened()) {
            System.out.println("无法打开摄像头");
            return;
        }

        // 设置摄像头分辨率
        capture.set(Videoio.CAP_PROP_FRAME_WIDTH, 640);
        capture.set(Videoio.CAP_PROP_FRAME_HEIGHT, 480);

        Mat frame = new Mat();
        while (true) {
            // 从摄像头读取帧
            capture.read(frame);
            if (frame.empty()) {
                System.out.println("无法从摄像头读取帧");
                break;
            }

            // 检测并绘制人脸，并获取是否检测到人脸的布尔值
            boolean faceDetected = faceDetector.detectAndDrawFaces(frame);

            // 如果检测到人脸，则在控制台输出提示信息
            if (faceDetected) {
                System.out.println("检测到人脸！");
            }

            // 显示结果图像
            HighGui.imshow("人脸检测", frame);

            // 按 'q' 退出
            if (HighGui.waitKey(1) == 'q') {
                break;
            }
        }

        // 释放摄像头资源
        capture.release();
        // 关闭所有OpenCV窗口
        HighGui.destroyAllWindows();
    }
}
