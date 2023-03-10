package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Object extends ShaderProgram{

    List<Vector3f> vertices;
    int vao;
    int vbo;

    int vboColor;
    Vector4f color;
    UniformsMap uniformsMap;
    List<Vector3f> verticesColor;
    List<Vector3f> verticesBezier;

    // kalo mau pake uni_color
    public Object(List<ShaderModuleData> shaderModuleDataList
            , List<Vector3f> vertices, Vector4f color) {
        super(shaderModuleDataList);
        this.vertices = vertices;
        this.color = color;
        setupVAOVBO();
        uniformsMap = new UniformsMap(getProgramId());
        uniformsMap.createUniform("uni_color");
    }
    //kalo mau pake out_color
    public Object(List<ShaderModuleData> shaderModuleDataList
            , List<Vector3f> vertices, List<Vector3f> verticesColor) {
        super(shaderModuleDataList);
        this.vertices = vertices;
        this.verticesColor = verticesColor;
//        setupVAOVBO();
        setupVAOVBOWithVerticesColor();

    }
    public Object(List<ShaderModuleData> shaderModuleDataList
            , List<Vector3f> vertices, List<Vector3f> verticesBezier, Vector4f color) {
        super(shaderModuleDataList);
        this.vertices = vertices;
        this.verticesBezier = verticesBezier;
        this.color = color;
        setupVAOVBOCurve();
        uniformsMap = new UniformsMap(getProgramId());
        uniformsMap.createUniform("uni_color");
    }
    public void setupVAOVBO() {
        //set vao
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        //set vbo
        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER,
                Utils.listoFloat(vertices),
                GL_STATIC_DRAW);
    }
    public void setupVAOVBOWithVerticesColor(){
        //set vao
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        //set vbo
        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER,
                Utils.listoFloat(vertices),
                GL_STATIC_DRAW);

        vboColor = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboColor);
        glBufferData(GL_ARRAY_BUFFER,
                Utils.listoFloat(verticesColor),
                GL_STATIC_DRAW);
    }
    public void setupVAOVBOCurve() {
        //set vao
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        //set vbo
        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER,
                Utils.listoFloat(verticesBezier),
                GL_STATIC_DRAW);
    }
    public void drawSetup(){
        bind();
        uniformsMap.setUniform("uni_color", color);
        // Bind VBO
        glEnableVertexAttribArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexAttribPointer(0, 3,
                GL_FLOAT,
                false,
                0, 0);
    }
    public void drawSetupCurve(){
        bind();
        uniformsMap.setUniform("uni_color", color);
        // Bind VBO
        glEnableVertexAttribArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexAttribPointer(0, 3,
                GL_FLOAT,
                false,
                0, 0);
    }
    public void drawSetupWithVerticesColor(){
        bind();
        // Bind VBO
        glEnableVertexAttribArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexAttribPointer(0, 3,
                GL_FLOAT,
                false,
                0, 0);

        glEnableVertexAttribArray(1);
        glBindBuffer(GL_ARRAY_BUFFER, vboColor);
        glVertexAttribPointer(1, 3,
                GL_FLOAT,
                false,
                0, 0);
    }
    public void draw(){
        drawSetup();
        // Draw the vertices
        //optional
        glLineWidth(15); //ketebalan garis
        glPointSize(10); //besar kecil vertex
        //wajib
        //GL_LINES
        //GL_LINE_STRIP
        //GL_LINE_LOOP
        //GL_TRIANGLES
        //GL_TRIANGLE_FAN
        //GL_POINT
//        glDrawArrays(GL_TRIANGLES,
//                0,
//                vertices.size());
        glDrawArrays(GL_POLYGON,
                0,
                vertices.size());
//        glDrawArrays(GL_LINES,
//                0,
//                vertices.size());
//        glDrawArrays(GL_LINES,
//                1,
//                vertices.size());
//        glDrawArrays(GL_LINES,
//                2,
//                vertices.size());
    }

    public void drawWithVerticesColor(){
        drawSetupWithVerticesColor();
        // Draw the vertices
        //optional
        glLineWidth(15); //ketebalan garis
        glPointSize(10); //besar kecil vertex
        //wajib
        //GL_LINES
        //GL_LINE_STRIP
        //GL_LINE_LOOP
        //GL_TRIANGLES -> bikin segitiganya 2x, garisnya ada 2 bnre, tp kek ketumpuk gitu, jd g keliatan
        //GL_TRIANGLE_FAN -> kalo ada 2 jejer, triangle fan isa tau ada garis sg isa dipake gak,
        // dia minjem trs nambah 2 garis gt, bkn bikin garis dewe
        //GL_POINT
//        glDrawArrays(GL_TRIANGLES,
//                0,
//                vertices.size());
        glDrawArrays(GL_TRIANGLES,
                0,
                vertices.size());

    }
    public void drawLines() {
        drawSetup();

        glLineWidth(1); //ketebalan garis
        glPointSize(1); //besar kecil vertex

        glDrawArrays(GL_LINE_LOOP,
                0,
                vertices.size());
    }

    public List<Vector3f> getVertices() {
        return vertices;
    }
    public List<Vector3f> getVerticesBezier() {
        return verticesBezier;
    }
    public void drawLine(){
        drawSetup();
        glLineWidth(5); //ketebalan garis
        glPointSize(5); //besar kecil vertex
        glDrawArrays(GL_LINE_STRIP,
                0,
                vertices.size());
    }

    public void addVertices(Vector3f newVector){
        vertices.add(newVector);
        setupVAOVBO();
    }

    public void setVertices(int index, Vector3f newVector){
        vertices.set(index, newVector);
        setupVAOVBO();
    }
    public void addVerticesCurve(Vector3f newVector){
        vertices.add(newVector);
        verticesBezier = createBezier(vertices.size(), vertices);
        setupVAOVBOCurve();
    }
    public void setVerticesCurve(int index, Vector3f newVector){
        vertices.set(index, newVector);
        verticesBezier = createBezier(vertices.size(), vertices);
        setupVAOVBOCurve();
    }
    public static int fact(int n){
        if (n == 0) return 1;
        return n*fact(n-1);
    }
    public List<Vector3f> createBezier(int n, List<Vector3f> vertices){
        boolean cekBezier = true;
        verticesBezier = new ArrayList<>();
        for (float t = 0; t < 1; t += 0.01f) {
            float x = 0.0f;
            float y = 0.0f;
            for (int i = 0; i < n; i++) {
                x += (float) (fact(n - 1) / (fact(i) * fact((n - 1) - i)) * Math.pow(1 - t, ((n - 1) - i)) * Math.pow(t, i)) * vertices.get(i).x;
                y += (float) (fact(n - 1) / (fact(i) * fact((n - 1) - i)) * Math.pow(1 - t, ((n - 1) - i)) * Math.pow(t, i)) * vertices.get(i).y;
            }
            for (int j = 0; j < verticesBezier.size(); j++) {
                if (verticesBezier.get(j).x == x && verticesBezier.get(j).y == y){
                    cekBezier = false;
                }
            }
            if (cekBezier == true){
                verticesBezier.add(new Vector3f(x, y, 0.0f));
            }
            cekBezier = true;
        }

        return verticesBezier;
    }
    public void drawCurve(){
        drawSetupCurve();
        glLineWidth(5); //ketebalan garis
        glPointSize(5); //besar kecil vertex
//        System.out.println(vertices);
//        verticesBezier = createBezier(vertices.size(), vertices);
//        for (int i = 0; i < verticesBezier.size(); i++) {
//            System.out.print(verticesBezier.get(i).x + " ");
//        }
        glDrawArrays(GL_LINE_STRIP,
                0,
                verticesBezier.size());
//        System.out.println("TEST");
    }

}
