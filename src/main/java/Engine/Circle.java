package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL15.*;

public class Circle extends Object {
    List<Integer> index;
    int ibo;
    float radius, radius_x, radius_y;
    Vector3f centerPoint;

    public Circle(List<ShaderModuleData> shaderModuleDataList,
                  List<Vector3f> vertices, Vector4f color, float x, float y, float z, int degree, float radius) {
        super(shaderModuleDataList, vertices, color);
        this.color = color;
        this.radius = radius;
        this.centerPoint =  new Vector3f(x,y,z);
        this.vertices = new ArrayList<>(createCircle(x,y,z,degree));
        setupVAOVBO();
        uniformsMap = new UniformsMap(getProgramId());
        uniformsMap.createUniform("uni_color");
    }
    public Circle(List<ShaderModuleData> shaderModuleDataList,
                  List<Vector3f> vertices, Vector4f color, float x, float y, float z, int degree, float radius_x, float radius_y) {
        super(shaderModuleDataList, vertices, color);
        this.color = color;
        this.centerPoint =  new Vector3f(x,y,z);
        this.radius_x = radius_x;
        this.radius_y = radius_y;
        this.vertices = new ArrayList<>(createEllipse(x,y,z,degree));
        setupVAOVBO();
        uniformsMap = new UniformsMap(getProgramId());
        uniformsMap.createUniform("uni_color");
    }


    public Vector3f editCircle(float x, float y, float z){
        this.vertices = new ArrayList<>();
        this.vertices = createCircle(x,y,z,45);
        return (Vector3f) vertices;
    }
    public ArrayList<Vector3f> createCircle(float x, float y, float z, int degree){
        ArrayList<Vector3f> vertices = new ArrayList<>();
        float penambah_angle = 0.01f, angle_awal = 0.0f;
        if (degree == 45){
            penambah_angle = 90f;
            angle_awal = 45f;
        }
        if (degree == 120){
            angle_awal = 90;
            penambah_angle = 120f;
        }
        for (float angle = angle_awal; angle <= 360; angle += penambah_angle) {
            vertices.add(new Vector3f((float) ((radius * Math.cos(Math.toRadians(angle))) + x),
                    (float) ((radius * Math.sin(Math.toRadians(angle))) + y), 0.0f));
        }
        return vertices;
        // vertices clear dulu, dri codingan minggu 1, taruh di sini, vertices i+=0.01f sdh cukup, dripd ngelag

    }
    public ArrayList<Vector3f> createEllipse(float x, float y, float z, int degree){
        ArrayList<Vector3f> vertices = new ArrayList<>();
        float penambah_angle = 0.01f, angle_awal = 0.0f;
        if (degree == 45){
            penambah_angle = 90f;
            angle_awal = 45f;
        }
        if (degree == 120){
            angle_awal = 90;
            penambah_angle = 120f;
        }
        for (float angle = angle_awal; angle <= 360; angle += penambah_angle) {
            vertices.add(new Vector3f((float) ((radius_x * Math.cos(Math.toRadians(angle))) + x),
                    (float) ((radius_y * Math.sin(Math.toRadians(angle))) + y), 0.0f));
        }
        return vertices;
        // vertices clear dulu, dri codingan minggu 1, taruh di sini, vertices i+=0.01f sdh cukup, dripd ngelag

    }
    public void draw() {
        drawSetup();

        glLineWidth(1); //ketebalan garis
        glPointSize(1); //besar kecil vertex

        glDrawArrays(GL_POLYGON,
                0,
                vertices.size());
    }

//    public void draw() {
//        drawSetup();
//
//        glLineWidth(1); //ketebalan garis
//        glPointSize(1); //besar kecil vertex
//
//        glDrawArrays(GL_LINE_STRIP,
//                0,
//                vertices.size());
//    }
//    public void draw() {
//        drawSetup();
//
//        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
//
//        glDrawElements(GL_TRIANGLES,
//                vertices.size(),
//                GL_UNSIGNED_INT,0);
//    }
}
