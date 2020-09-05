package ch01.ex06;

// Runnable interface Exception を投げるものとして扱うため
// 何故 Callable ではだめか．まず実装メソッドが異なる．
// 使用のされ方も異なる．
// https://codechacha.com/ja/java-callable-vs-runnable/
interface RunnableEx {
    void run() throws Exception;
}
