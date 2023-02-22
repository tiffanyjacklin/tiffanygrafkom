import Engine.*;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL30.*;

public class Main {
    private Window window =
            new Window
                    (800,800,"Hello World");
    private ArrayList<Object2d> objects
            = new ArrayList<>();
    private ArrayList<Object2d> objectsLinesStar
            = new ArrayList<>();
    private ArrayList<Object2d> objectsRectangle
            = new ArrayList<>();
    private ArrayList<Object2d> objectsParallelogram
            = new ArrayList<>();
    private ArrayList<Object2d> objectsHome
            = new ArrayList<>();
    private ArrayList<Object2d> objectsChimney
            = new ArrayList<>();
    private ArrayList<Object2d> objectsCircle
            = new ArrayList<>();

    public void init(){
        window.init();
        GL.createCapabilities();

        // langit bro tp pake circle
        objectsCircle.add(new Circle(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.2f, 0.2f, 1.0f, 1.0f),
                0.0f,0.2f,0.0f,45,1.42f,1.13f

        ));
        // rumput bro pake lingkaran
        objectsCircle.add(new Circle(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.5f, 0.0f, 1.0f),
                0.0f,-0.8f,0.0f,45,1.42f,0.29f

        ));

        // merah atap using circle
        objectsHome.add(new Circle(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 0.0f, 0.0f, 1.0f),
                -0.5f,-0.165f,0.0f,120,0.23f,0.263f
        ));
        // kuning rumah kotak pake circle
        objectsHome.add(new Circle(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.8f, 0.5f, 0.1f, 1.0f),
                0.0f,-0.45f,0.0f,45,0.85f,0.35f

        ));

        // merah atap jajar genjang
        objectsParallelogram.add(new Rectangle(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.301f,-0.3f,0.0f),
                                new Vector3f(0.7f,-0.3f,0.0f),
                                new Vector3f(-0.501f,0.1f,0.0f),
                                new Vector3f(0.5f,0.1f,0.0f)
                        )
                ),
                new Vector4f(1.0f, 0.0f, 0.0f, 1.0f),
                Arrays.asList(0,1,2,1,2,3)
        ));

        // kuning cerobong pake circle
        objectsChimney.add(new Circle(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.8f, 0.5f, 0.1f, 1.0f),
                0.305f,0.1f,0.0f,45,0.07f,0.14f

        ));

        // ujung cerobong pake circle
        objectsChimney.add(new Circle(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.6f, 0.2f, 0.1f, 1.0f),
                0.305f,0.2f,0.0f,45,0.11f,0.03f

        ));

        // bulan kuning
        objectsCircle.add(new Circle(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 0.0f, 1.0f),
                -0.7f,0.7f,0.0f,0,0.1f

        ));
        // bulan biru biar kek crescent
        objectsCircle.add(new Circle(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.2f, 0.2f, 1.0f, 1.0f),
                -0.65f,0.7f,0.0f,0,0.1f

        ));
        // asep atas
        objectsCircle.add(new Circle(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.5f, 0.5f, 0.5f, 1.0f),
                0.455f,0.4f,0.0f,0,0.15f, 0.05f

        ));
        // asep tengah
        objectsCircle.add(new Circle(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.5f, 0.5f, 0.5f, 1.0f),
                0.355f,0.348f,0.0f,0,0.1f, 0.05f

        ));
        // asep bawah
        objectsCircle.add(new Circle(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.5f, 0.5f, 0.5f, 1.0f),
                0.305f,0.3f,0.0f,0,0.075f, 0.05f

        ));
        // segitiga kuning using circle
        objectsHome.add(new Circle(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.8f, 0.5f, 0.1f, 1.0f),
                -0.475f,-0.115f,0.0f,120,0.145f,0.18f
        ));


        // star kiri besar
        objectsLinesStar.add(new Object2d(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.315f,0.455f,0.0f),
                                new Vector3f(-0.315f,0.55f,0.0f),
                                new Vector3f(-0.26f,0.47f,0.0f),
                                new Vector3f(-0.35f,0.505f,0.0f),
                                new Vector3f(-0.26f,0.53f,0.0f)
                        )
                ),
                new Vector4f(1.0f, 1.0f, 0.0f, 1.0f)
        ));

        // star tengah kecil
        objectsLinesStar.add(new Object2d(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.005f,0.7775f,0.0f),
                                new Vector3f(-0.005f,0.825f,0.0f),
                                new Vector3f(0.0225f,0.785f,0.0f),
                                new Vector3f(-0.0225f,0.8025f,0.0f),
                                new Vector3f(0.0225f,0.815f,0.0f)
                        )
                ),
                new Vector4f(1.0f, 1.0f, 0.0f, 1.0f)
        ));

        // star kanan besar
        objectsLinesStar.add(new Object2d(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(0.69f,0.655f,0.0f),
                                new Vector3f(0.69f,0.75f,0.0f),
                                new Vector3f(0.745f,0.67f,0.0f),
                                new Vector3f(0.655f,0.705f,0.0f),
                                new Vector3f(0.745f,0.73f,0.0f)
                        )
                ),
                new Vector4f(1.0f, 1.0f, 0.0f, 1.0f)
        ));
    }
    public void loop(){
        while (window.isOpen()) {
            window.update();
            glClearColor(0.0f,
                    0.0f, 0.0f,
                    0.0f);
            GL.createCapabilities();

            //code
//            for(Object2d object: objectsLinesStar){
//                object.draw();
//            }
            for(Object2d object: objectsCircle){
                object.draw();
            }
            for(Object2d object: objects){
                object.drawWithVerticesColor();
            }

            for(Object2d object: objectsRectangle){
                object.draw();
            }
            for(Object2d object: objectsHome){
                object.draw();
            }
            for(Object2d object: objectsParallelogram){
                object.draw();
            }

            for(Object2d object: objectsLinesStar){
                object.drawLines();
            }
            for(Object2d object: objectsChimney){
                object.draw();
            }
            // Restore state
            glDisableVertexAttribArray(0);

            // Poll for window events.
            // The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }
    public void run() {

        init();
        loop();

        // Terminate GLFW and
        // free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }
    public static void main(String[] args) {
        new Main().run();
    }
}