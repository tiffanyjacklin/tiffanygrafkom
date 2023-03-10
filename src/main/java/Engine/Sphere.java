package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;

public class Sphere extends Circle{
    float radius_z;
    List<Integer> index;
    int ibo;

    public Sphere(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, float x, float y, float z, int degree, float radius_x, float radius_y, float radius_z) {
        super(shaderModuleDataList, vertices, color, x, y, z, degree, radius_x, radius_y);
        this.radius_z = radius_z;
        this.centerPoint =  new Vector3f(x,y,z);
//        createBox();
//        createSphere();
//        createEllipsoid(radius_x, radius_y, radius_z);
//        createHyperboloid1(radius_x, radius_y, radius_z);
//        createHyperboloid2(radius_x, radius_y, radius_z);
//        createEllipticCone(radius_x, radius_y, radius_z);
//        createEllipticenterPointaraboloid(radius_x, radius_y);
        createHyperboloidParaboloid(radius_x, radius_y);
        setupVAOVBO();

    }
    public void createSphere(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();
        int stackCount = 18, sectorCount = 36;
        float x,y,z,xy,nx,ny,nz;
        float sectorStep = (float)(2* Math.PI )/ sectorCount; //sector count
        float stackStep = (float)Math.PI / stackCount; // stack count
        float sectorAngle, stackAngle;
        // sphere
        for(int i=0;i<=stackCount;i++){
            stackAngle = (float)Math.PI/2 - i * stackStep;
            xy = (float) (0.5f * Math.cos(stackAngle));
            z = (float) (0.5f * Math.sin(stackAngle));
            for(int j=0;j<sectorCount;j++){
                sectorAngle = j * sectorStep;
                x = (float) (xy * Math.cos(sectorAngle));
                y = (float) (xy * Math.sin(sectorAngle));
                temp.add(new Vector3f(x,y,z));
            }
        }
        vertices = temp;

        int k1, k2;
        ArrayList<Integer> temp_indices = new ArrayList<>();
        for(int i=0;i<stackCount;i++){
            k1 = i * (sectorCount + 1);
            k2 = k1 + sectorCount + 1;
            for(int j=0;j<sectorCount;++j, ++k1, ++k2){
                if(i != 0){
                    temp_indices.add(k1);
                    temp_indices.add(k2);
                    temp_indices.add(k1+1);
                }
                if(i!=(18-1)){
                    temp_indices.add(k1+1);
                    temp_indices.add(k2);
                    temp_indices.add(k2+1);

                }
            }
        }
        this.index = temp_indices;
        ibo = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER,
                Utils.listoInt(index),
                GL_STATIC_DRAW);

    }

    public void createEllipsoid(float radius_x, float radius_y, float radius_z){
        ArrayList<Vector3f> temp = new ArrayList<>();

        for(double v = -Math.PI/2; v<= Math.PI/2; v+=Math.PI/60){
            for(double u = -Math.PI; u<= Math.PI; u+=Math.PI/60){
                float x = radius_x * (float)(Math.cos(v) * Math.cos(u));
                float y = radius_y * (float)(Math.cos(v) * Math.sin(u));
                float z = radius_z * (float)(Math.sin(v));
                temp.add(new Vector3f(x,z,y));
            }
        }
        vertices.clear();
        vertices = temp;
    }

    public void createHyperboloid1(float radius_x, float radius_y, float radius_z){
        ArrayList<Vector3f> temp = new ArrayList<>();
        for (double v = -Math.PI / 2; v <= Math.PI/2; v += Math.PI / 60){
            for (double u = -Math.PI; u <= Math.PI; u += Math.PI / 60){
                float x = radius_x * (float) (1 / Math.cos(v) * Math.cos(u));
                float y = radius_y * (float) (1 / Math.cos(v) * Math.sin(u));
                float z = radius_z * (float) (Math.tan(v));
                temp.add(new Vector3f(x, z, y));
            }
        }
        vertices.clear();
        vertices = temp;
    }
    public void createHyperboloid2(float radius_x, float radius_y, float radius_z){
        ArrayList<Vector3f> temp = new ArrayList<>();
        for (double v = -Math.PI / 2; v <= Math.PI / 2; v += Math.PI / 60) {
            for (double u = -Math.PI / 2; u <= Math.PI / 2; u += Math.PI / 60) {
                float x = radius_x * (float) (Math.tan(v) * Math.cos(u));
                float y = radius_y * (float) (Math.tan(v) * Math.sin(u));
                float z = radius_z * (float) (1 / Math.cos(v));
                temp.add(new Vector3f(x, z, y));
            }

        }
        for (double v = -Math.PI / 2; v <= Math.PI / 2; v += Math.PI / 60) {
            for (double u = Math.PI / 2; u <= 3 * Math.PI / 2; u += Math.PI / 60) {
                float x = radius_x * (float) (Math.tan(v) * Math.cos(u));
                float y = radius_y * (float) (Math.tan(v) * Math.sin(u));
                float z = -radius_z * (float) (1 / Math.cos(v));
                temp.add(new Vector3f(x, z, y));
            }
        }
        vertices.clear();
        vertices = temp;
    }
    public void createEllipticCone(float radius_x, float radius_y, float radius_z){
        ArrayList<Vector3f> temp = new ArrayList<>();
        for (double v = -Math.PI / 2; v <= Math.PI / 2; v += Math.PI / 60) {
            for (double u = -Math.PI; u <= Math.PI; u += Math.PI / 60) {
                float x = radius_x * (float) (v * Math.cos(u));
                float y = radius_y * (float) (v * Math.sin(u));
                float z = (float) (radius_z * v);
                temp.add(new Vector3f(z,y,x));
            }
        }
        vertices.clear();
        vertices = temp;
    }
    public void createEllipticenterPointaraboloid(float radius_x, float radius_y){
        ArrayList<Vector3f> temp = new ArrayList<>();
        for (double v = 0; v <= 1; v += 0.05) {
            for (double u = -Math.PI; u <= Math.PI; u += Math.PI / 60) {
                float x = radius_x * (float) (v * Math.cos(u));
                float y = radius_y * (float) (v * Math.sin(u));
                float z = (float) Math.pow(v, 2);
                temp.add(new Vector3f(z,y,x));
            }
        }
        vertices.clear();
        vertices = temp;
    }
    public void createHyperboloidParaboloid(float radius_x, float radius_y){
        ArrayList<Vector3f> temp = new ArrayList<>();
        for (double v = 0; v <= 1; v += 0.05) {
            for (double u = -Math.PI; u <= Math.PI; u += Math.PI / 60) {
                float x = radius_x * (float) (v * Math.tan(u));
                float y = radius_y * (float) (v * 1/Math.sin(u));
                float z = (float) Math.pow(v, 2);
                temp.add(new Vector3f(x, y, z));
            }
        }
        vertices.clear();
        vertices = temp;
    }

    public void draw(){
        drawSetup();
        glLineWidth(2); //ketebalan garis
        glPointSize(2); //besar kecil vertex
        glDrawArrays(GL_LINE_STRIP,
                0,
                vertices.size());
    }
    public void drawIndicies(){
        drawSetup();
        // Draw the vertices
        //optional
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,ibo);

        glDrawElements(GL_TRIANGLES,
                index.size(),GL_UNSIGNED_INT,0);
    }

    public void createBox(){
        Vector3f temp = new Vector3f();
        ArrayList<Vector3f> tempVertices = new ArrayList<>();
        //TITIK 1
        temp.x = centerPoint.get(0) - radius_x / 2.0f;
        temp.y = centerPoint.get(1) + radius_y / 2.0f;
        temp.z = centerPoint.get(2) - radius_z / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 2
        temp.x = centerPoint.get(0) + radius_x / 2.0f;
        temp.y = centerPoint.get(1) + radius_y / 2.0f;
        temp.z = centerPoint.get(2) - radius_z / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 3
        temp.x = centerPoint.get(0) + radius_x / 2.0f;
        temp.y = centerPoint.get(1) - radius_y / 2.0f;
        temp.z = centerPoint.get(2) - radius_z / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 4
        temp.x = centerPoint.get(0) - radius_x / 2.0f;
        temp.y = centerPoint.get(1) - radius_y / 2.0f;
        temp.z = centerPoint.get(2) - radius_z / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 5
        temp.x = centerPoint.get(0) - radius_x / 2.0f;
        temp.y = centerPoint.get(1) + radius_y / 2.0f;
        temp.z = centerPoint.get(2) + radius_z / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 6
        temp.x = centerPoint.get(0) + radius_x / 2.0f;
        temp.y = centerPoint.get(1) + radius_y / 2.0f;
        temp.z = centerPoint.get(2) + radius_z / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 7
        temp.x = centerPoint.get(0) + radius_x / 2.0f;
        temp.y = centerPoint.get(1) - radius_y / 2.0f;
        temp.z = centerPoint.get(2) + radius_z / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 8
        temp.x = centerPoint.get(0) - radius_x / 2.0f;
        temp.y = centerPoint.get(1) - radius_y / 2.0f;
        temp.z = centerPoint.get(2) + radius_z / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();

        vertices.clear();
        //kotak yg sisi belakang
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(2));
        vertices.add(tempVertices.get(3));
        //kotak yg sisi depan
        vertices.add(tempVertices.get(4));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(6));
        vertices.add(tempVertices.get(7));
        //kotak yg sisi kiri
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(4));
        vertices.add(tempVertices.get(7));
        vertices.add(tempVertices.get(3));
        //kotak yg sisi kanan
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(6));
        vertices.add(tempVertices.get(2));
        //kotak yg sisi atas
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(4));
        //kotak yg sisi bawah
        vertices.add(tempVertices.get(3));
        vertices.add(tempVertices.get(2));
        vertices.add(tempVertices.get(7));
        vertices.add(tempVertices.get(6));

    }
}
