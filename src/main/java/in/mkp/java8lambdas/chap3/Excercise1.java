package in.mkp.java8lambdas.chap3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import in.mkp.java8lambdas.chap1.Album;
import in.mkp.java8lambdas.chap1.Artist;
import in.mkp.java8lambdas.chap1.SampleData;

import static java.util.stream.Collectors.toList;

/**
 * Created by mkumar on 5/2/17.
 */
public class Excercise1 {
    public static int addUp(Stream<Integer> numbers) {
        return numbers.reduce(0, (a, b) -> a+b);
    }

    public static List<String> getNamesAndOrigins(List<Artist> artists) {
        return artists.stream().map(a -> a.getName()+ "\t" + a.getNationality()).collect(Collectors.toList());
    }

    /*public static List<String> getNamesAndOrigins(List<Artist> artists) {
        return artists.stream()
                .flatMap(artist -> Stream.of(artist.getName(), artist.getNationality()))
                .collect(toList());
    }*/

    public static List<Album> getAlbumsWithAtMostThreeTracks(List<Album> input) {
        return input.stream().filter(a -> a.getTrackList().size() <=3 ).collect(toList());
    }

    public static void main(String[] args) {
        Integer[] a = {1, 2, 3, 4 , 5};
        System.out.println("addUp() = " + addUp(Arrays.stream(a)));

        System.out.println("getNamesAndOrigins(SampleData.getThreeArtists()) = " + getNamesAndOrigins(SampleData.getThreeArtists()));
    }
}
