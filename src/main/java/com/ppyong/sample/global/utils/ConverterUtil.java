package com.ppyong.sample.global.utils;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ConverterUtil {
    private static final ModelMapper modelMapper;
    private static final ModelMapper modelMapperForSkipNull;

    static {
        // 두개 선언 할 수 밖에 없을까?
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.getConfiguration().setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        modelMapper.getConfiguration().setFieldMatchingEnabled(true);

        modelMapperForSkipNull = new ModelMapper();
        modelMapperForSkipNull.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapperForSkipNull.getConfiguration().setSkipNullEnabled(true);
        modelMapperForSkipNull.getConfiguration().setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        modelMapperForSkipNull.getConfiguration().setFieldMatchingEnabled(true);
    }

    public static <T, R> R map(final T source, Class<R> clazz) {
        return modelMapper.map(source, clazz);
    }

    public static <T, R> List<R> map(final List<? extends T> sources, Class<R> clazz) {
        return sources.stream().map(i->map(i, clazz)).collect(Collectors.toList());
    }

    public static <T, R> void map(final T source, R dest) {
        modelMapper.map(source, dest);
    }

    public static <T, R> void skipNullMap(final T source, R dest) {
        modelMapperForSkipNull.map(source, dest);
    }
}
