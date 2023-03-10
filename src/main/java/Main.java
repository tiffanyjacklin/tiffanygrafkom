import Engine.*;
import Engine.Object;
import org.joml.Vector2f;
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
    private ArrayList<Object> objects
            = new ArrayList<>();
    private ArrayList<Object> objectsLinesStar
            = new ArrayList<>();
    private ArrayList<Object> objectsRectangle
            = new ArrayList<>();
    private ArrayList<Object> objectsParallelogram
            = new ArrayList<>();
    private ArrayList<Object> objectsHome
            = new ArrayList<>();
    private ArrayList<Object> objectsChimney
            = new ArrayList<>();
    private ArrayList<Object> objectsCircle
            = new ArrayList<>();

    ArrayList<Object> objectsPointsControl = new ArrayList<>();
    ArrayList<Object> objectsCurve = new ArrayList<>();


    public void init(){
        window.init();
        GL.createCapabilities();
        objects.add(new Sphere(
                Arrays.asList(
                //shaderFile lokasi menyesuaikan objectnya
                new ShaderProgram.ShaderModuleData
                        ("resources/shaders/scene.vert"
                                , GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData
                        ("resources/shaders/scene.frag"
                                , GL_FRAGMENT_SHADER)
            ),
            new ArrayList<>(
                    List.of(
                            new Vector3f(0.5f,0.5f,0.0f),
                            new Vector3f(-0.5f,0.5f,0.0f),
                            new Vector3f(-0.5f,-0.5f,0.0f),
                            new Vector3f(0.5f,-0.5f,0.0f)
                    )
                ),
            new Vector4f(0.0f, 1.0f, 1.0f, 1.0f),
            0.0f, 0.0f, 0.0f, 45, 0.5f, 0.5f, 0.5f
        ));
//        objectsPointsControl.add(new Object(
//            Arrays.asList(
//                    new ShaderProgram.ShaderModuleData
//                            ("resources/shaders/scene.vert"
//                                    , GL_VERTEX_SHADER),
//                    new ShaderProgram.ShaderModuleData
//                            ("resources/shaders/scene.frag"
//                                    , GL_FRAGMENT_SHADER)
//            ),
//            new ArrayList<>(),
//            new Vector4f(0.0f, 0.0f, 0.0f, 1.0f)
//
//        ));
//        objectsCurve.add(new Object(
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData
//                                ("resources/shaders/scene.vert"
//                                        , GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData
//                                ("resources/shaders/scene.frag"
//                                        , GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(), new ArrayList<>(),
//                new Vector4f(1.0f, 0.5f, 0.7f, 1.0f)
//        ));
    }
    public void input(){
//        if (window.isKeyPressed(GLFW_KEY_W)){
//            System.out.println("W");
//        }
//        if (window.getMouseInput().isLeftButtonPressed()){
//            boolean check = true;
//            Vector2f pos = window.getMouseInput().getCurrentPos();
//            pos.x = (pos.x - (window.getWidth())/2.0f) / (window.getWidth()/2.0f);
//            pos.y = (pos.y - (window.getHeight())/2.0f) / (-window.getHeight()/2.0f);
//
//            if ((!(pos.x > 1 || pos.x < -0.97) && !(pos.y > 0.97 || pos.y < -1) && (pos.x != 0.0 && pos.y != 0.0))){
////                System.out.println("x : "+pos.x+" y : "+pos.y);
//                for (int i = 0; i < objectsCircle.size(); i++) {
//                    System.out.println(objectsCircle.get(i).getVertices());
//                    if ((pos.x >= objectsCircle.get(i).getVertices().get(1).x && pos.x <= objectsCircle.get(i).getVertices().get(0).x)
//                            && (pos.y <= objectsCircle.get(i).getVertices().get(0).y && pos.y >= objectsCircle.get(i).getVertices().get(3).y)) {
//                        if ((!(pos.x > 1 || pos.x < -0.97) && !(pos.y > 0.97 || pos.y < -1) && (pos.x != 0.0 && pos.y != 0.0))) {
//                            objectsCircle.set(i, new Circle(
//                                    Arrays.asList(
//                                            new ShaderProgram.ShaderModuleData
//                                                    ("resources/shaders/scene.vert"
//                                                            , GL_VERTEX_SHADER),
//                                            new ShaderProgram.ShaderModuleData
//                                                    ("resources/shaders/scene.frag"
//                                                            , GL_FRAGMENT_SHADER)
//                                    ),
//                                    new ArrayList<>(),
//                                    new Vector4f(1.0f, 0.0f, 0.0f, 1.0f),
//                                    pos.x, pos.y, 0.0f, 45, 0.075f, 0.075f
//                            ));
//                            objectsPointsControl.get(0).setVertices(i, new Vector3f(pos.x, pos.y, 0.0f));
//                            objectsCurve.get(0).setVerticesCurve(i, new Vector3f(pos.x, pos.y, 0.0f));
//                            check = false;
//
//                        }
//                    }
//                }
//
////                System.out.println(objectsCurve.get(0).createBezier(objectsCurve.get(0).getVertices().size(), objectsCurve.get(0).getVertices()));
//                if (check){
//                    objectsPointsControl.get(0).addVertices(new Vector3f(pos.x, pos.y, 0));
//                    objectsCircle.add(new Circle(
//                            Arrays.asList(
//                                    new ShaderProgram.ShaderModuleData
//                                            ("resources/shaders/scene.vert"
//                                                    , GL_VERTEX_SHADER),
//                                    new ShaderProgram.ShaderModuleData
//                                            ("resources/shaders/scene.frag"
//                                                    , GL_FRAGMENT_SHADER)
//                            ),
//                            new ArrayList<>(),
//                            new Vector4f(1.0f, 0.0f, 0.0f, 1.0f),
//                            pos.x, pos.y, 0.0f, 45, 0.075f, 0.075f
//                    ));
//                    objectsCurve.get(0).addVerticesCurve(new Vector3f(pos.x, pos.y, 0));
//                }
//            }
//        }
    }
    public void loop(){
        while (window.isOpen()) {
            window.update();
            glClearColor(1.0f,
                    1.0f, 0.9f,
                    0.0f);
            GL.createCapabilities();
            input();
            //code
//            for(Object2d object: objectsLinesStar){
//                object.draw();
//            }

//            for(Object object: objectsCircle){
//                object.draw();
//            }
//            for(Object object: objectsPointsControl){
//                object.drawLine();
//            }
//            for(Object object: objectsCurve){
//                object.drawCurve();
//            }


            for(Object object: objects){
                object.draw();
            }
//
//            for(Object2d object: objectsRectangle){
//                object.draw();
//            }
//            for(Object2d object: objectsHome){
//                object.draw();
//            }
//            for(Object2d object: objectsParallelogram){
//                object.draw();
//            }
//
//            for(Object2d object: objectsLinesStar){
//                object.drawLines();
//            }
//            for(Object2d object: objectsChimney){
//                object.draw();
//            }

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