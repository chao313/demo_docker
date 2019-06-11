package demo.spring.boot.docker.util.ssh.other;

import java.io.IOException;
import java.io.InputStream;

public interface CallBack<T> {

    T deal(InputStream in) throws IOException;
}