package ch03.ex01;

import org.junit.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class LazyLoggerTest {
    @Test
    public void Test() throws Exception{
		int x = 1;
		int y = 2;
		// name - ロガーの名前。これはドットで区切られた名前にすべきであり、通常はjava.netやjavax.swingなど、サブシステムのパッケージ名またはクラス名に基づいた名前にすべきである。匿名Loggerの場合はnullも可能
        // resourceBundleName - このロガーのメッセージのローカライズに使用されるResourceBundleの名前。ローカリゼーションを必要とするメッセージが存在しない場合はnullも可能
		LazyLogger log = new LazyLogger(Logger.getLogger("lazy"));

		//logが出ることの確認
		log.logIf(Level.SEVERE, ()-> x == 1, () -> "x:"+x+" y:"+y);
		//logが出ないことの確認
		log.logIf(Level.SEVERE, ()-> x == 2, () -> "x:"+x+" y:"+y);
	}

}