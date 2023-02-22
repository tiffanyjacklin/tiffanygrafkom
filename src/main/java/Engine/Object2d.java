package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.List;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Object2d extends ShaderProgram{

    List<Vector3f> vertices;
    int vao;
    int vbo;

    int vboColor;
    Vector4f color;
    UniformsMap uniformsMap;
    List<Vector3f> verticesColor;


    // kalo mau pake uni_color
    public Object2d(List<ShaderModuleData> shaderModuleDataList
            , List<Vector3f> vertices, Vector4f color) {
        super(shaderModuleDataList);
        this.vertices = vertices;
        this.color = color;
        setupVAOVBO();
        uniformsMap = new UniformsMap(getProgramId());
        uniformsMap.createUniform("uni_color");
    }
    //kalo mau pake out_color
    public Object2d(List<ShaderModuleData> shaderModuleDataList
            , List<Vector3f> vertices, List<Vector3f> verticesColor) {
        super(shaderModuleDataList);
        this.vertices = vertices;
        this.verticesColor = verticesColor;
//        setupVAOVBO();
        setupVAOVBOWithVerticesColor();

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
        glDrawArrays(GL_LINE_LOOP,
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
}
