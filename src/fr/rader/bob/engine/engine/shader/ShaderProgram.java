package fr.rader.bob.engine.engine.shader;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Vector3f;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

public abstract class ShaderProgram {

    private final int fragmentShaderID;
    private final int vertexShaderID;
    private final int programID;

    private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);

    public ShaderProgram(String fragmentFile, String vertexFile) {
        fragmentShaderID = loadShader(fragmentFile, GL_FRAGMENT_SHADER);
        vertexShaderID = loadShader(vertexFile, GL_VERTEX_SHADER);

        // create shader program & attach shaders
        programID = glCreateProgram();
        glAttachShader(programID, fragmentShaderID);
        glAttachShader(programID, vertexShaderID);

        bindAttributes();

        // link & validate shader program
        glLinkProgram(programID);
        glValidateProgram(programID);

        getAllUniformLocations();
    }

    protected abstract void getAllUniformLocations();

    protected int getUniformLocation(String uniformName) {
        return glGetUniformLocation(programID, uniformName);
    }

    public void start() {
        glUseProgram(programID);
    }

    public void stop() {
        glUseProgram(0);
    }

    public void cleanup() {
        stop();

        glDetachShader(programID, fragmentShaderID);
        glDetachShader(programID, vertexShaderID);
        glDeleteShader(fragmentShaderID);
        glDeleteShader(vertexShaderID);
        glDeleteProgram(programID);
    }

    protected abstract void bindAttributes();

    protected void bindAttribute(int attribute, String variableName) {
        glBindAttribLocation(programID, attribute, variableName);
    }

    protected void loadFloat(int location, float value) {
        glUniform1f(location, value);
    }

    protected void loadVector(int location, Vector3f value) {
        glUniform3f(location, value.x, value.y, value.z);
    }

    protected void loadBoolean(int location, boolean value) {
        glUniform1f(location, value ? 1 : 0);
    }

    protected void loadMatrix(int location, Matrix4f matrix) {
        matrix.store(matrixBuffer);
        matrixBuffer.flip();
        glUniformMatrix4(location, false, matrixBuffer);
    }

    private static int loadShader(String file, int type) {
        StringBuilder shaderSource = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while((line = reader.readLine()) != null) {
                shaderSource.append(line).append("//\n");
            }

            reader.close();
        } catch(IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        int shaderID = glCreateShader(type);
        glShaderSource(shaderID, shaderSource);
        glCompileShader(shaderID);

        if(glGetShaderi(shaderID, GL_COMPILE_STATUS) == GL_FALSE){
            System.out.println(glGetShaderInfoLog(shaderID, 500));
            System.err.println("Could not compile shader!");
            System.exit(-1);
        }

        return shaderID;
    }
}
