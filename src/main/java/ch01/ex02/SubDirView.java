package ch01.ex02;

import java.io.File;
import java.util.Arrays;

public class SubDirView {
	public static void main(String[] args) {
		String path = new File(".").getAbsoluteFile().getParent();
		File file = new File(path);
		System.out.println(path + "のサブディレクトリをラムダ式で表示します。");
		Arrays.stream(SubDirView.listSubDir_lambda(file)).forEach(arr -> System.out.println(arr));
		System.out.println(path + "のサブディレクトリをメソッド参照で表示します。");
		Arrays.stream(SubDirView.listSubDir_method(file)).forEach(arr -> System.out.println(arr));
	}

	static File[] listSubDir_lambda(File file) {
		return file.listFiles(targetFile -> targetFile.isDirectory());
	}

	static File[] listSubDir_method(File file) {
		return file.listFiles(File::isDirectory);
	}
}
