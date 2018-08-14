package in.mkp.java8lambdas.chap4;

import java.util.List;
import java.util.Map;

import in.mkp.java8lambdas.chap1.Artist;
import in.mkp.java8lambdas.chap1.SampleData;

import static java.util.stream.Collectors.partitioningBy;

public class Example8 {

    public static void main(String[] args) {
        List<Artist> artistList = SampleData.getThreeArtists();
        final Map<Boolean, List<Artist>> collect = artistList.stream().collect(partitioningBy(artist -> artist.isSolo()));
        System.out.println("collect =  " + collect.toString());
    }

}
