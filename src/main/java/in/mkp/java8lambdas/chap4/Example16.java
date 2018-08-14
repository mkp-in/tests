package in.mkp.java8lambdas.chap4;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import in.mkp.java8lambdas.chap1.Album;
import in.mkp.java8lambdas.chap1.Artist;
import in.mkp.java8lambdas.chap1.SampleData;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

public class Example16 {

    public static void main(String[] args) {
        final Stream<Album> albums = SampleData.albums;

        final Map<Artist, List<String>> collect = albums.collect(groupingBy(album -> album.getMainMusician(), mapping(album -> album.getName(), toList())));

        System.out.println("collect = " + collect.toString());
    }

}
