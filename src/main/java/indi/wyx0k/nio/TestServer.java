package indi.wyx0k.nio;

public class TestServer {
    public static void main(String[] args) {
        ok();
        System.out.print(ok()[1]);
    }
    public static int[] ok(){
        return new int[]{1,2};
    }
}
