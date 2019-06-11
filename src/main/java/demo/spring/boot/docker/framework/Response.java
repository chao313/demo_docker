package demo.spring.boot.docker.framework;

import java.util.ArrayList;
import java.util.List;

//import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//
//@Data
//@Setter
//@Getter
//@ToString
public class Response<T> {
    private String code;
    private String msg;
    private String error;
    private List<Exception> exceptions
            = new ArrayList<Exception>();
    private T content;

    public Response() {
    }

    public Response(String code, String msg, T content) {
        this.code = code;
        this.msg = msg;
        this.content = content;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Exception> getExceptions() {
        return exceptions;
    }

    public Response addException(Exception exception) {
        this.exceptions.add(exception);
        return this;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    /**
     * 请求失败
     *
     * @param <T>
     * @return
     */
    public static <T> Response<T> fail(T data) {
        return new Response(Code.System.FAIL, null, data);
    }

    /**
     * 请求失败
     *
     * @return
     */
    public static Response fail(String msg) {
        return new Response(Code.System.FAIL, msg, null);
    }


    /**
     * 请求失败
     *
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Response<T> fail(T data, String msg) {
        return new Response(Code.System.FAIL, msg, data);
    }

    /**
     * 请求ok
     *
     * @param <T>
     * @return
     */
    public static <T> Response<T> ok(T data) {
        return new Response(Code.System.OK, null, data);
    }

    /**
     * 请求ok
     *
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Response<T> ok(T data, String msg) {
        return new Response(Code.System.OK, msg, data);
    }


}
