package in.mkp.java8lambdas.chap3;

import java.util.List;

import in.mkp.java8lambdas.chap1.Artist;
import in.mkp.java8lambdas.chap1.SampleData;

public class Ex2 {

    public static void main(String[] args) {
        ex2();
        ex2_2();
    }

    private static void ex2() {
        List<Artist> artists = SampleData.getThreeArtists();
        System.out.println(artists.stream().mapToLong(a -> a.getMembers().count()).sum());

    }

    private static void ex2_2() {
        List<Artist> artists = SampleData.getThreeArtists();
        System.out.println(artists.stream().map(artist -> artist.getMembers().count()).reduce(0L, (a, b) -> a+b));

    }

}
