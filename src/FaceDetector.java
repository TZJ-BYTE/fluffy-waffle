import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class FaceDetector {
    private CascadeClassifier faceDetector;

    public FaceDetector(String classifierPath) {
        faceDetector = new CascadeClassifier();
        if (!faceDetector.load(classifierPath)) {
            throw new RuntimeException("无法加载人脸检测模型: " + classifierPath);
        }
    }

    /**
     * 检测并绘制人脸，返回是否检测到人脸的布尔值。
     *
     * @param frame 要检测的图像帧
     * @return 如果检测到人脸则返回true，否则返回false
     */
    public boolean detectAndDrawFaces(Mat frame) {
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(frame, faceDetections);

        Rect[] facesArray = faceDetections.toArray();
        boolean faceDetected = facesArray.length > 0;

        for (Rect rect : facesArray) {
            Imgproc.rectangle(frame, new org.opencv.core.Point(rect.x, rect.y),
                    new org.opencv.core.Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 255, 0), 2);
        }

        return faceDetected;
    }
}
