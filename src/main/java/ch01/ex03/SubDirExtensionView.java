package ch01.ex03;

import java.io.File;
import java.util.stream.Stream;

// エンクロージングスコープからキャプチャの意味が分かりませんでした．ラムダ式のスコープという意味でしたらメソッドの引数で受け取った引数を参照しています．
public class SubDirExtensionView {
    	public static void main(String[] args) {
		String path = new File(".").getAbsoluteFile().getParent();
		File file = new File(path);
		String extension = "gradle";
		File[] list = listSubDir(file, extension);
		for (File str : list) {
			System.out.println(str);
		}

	}

	public static File[] listSubDir(File f, String extension) {
		String[] list = f.list((file, name) -> name.endsWith(extension));
		return Stream.of(list).map(name -> new File(name)).toArray(i -> new File[i]);
	}

}
