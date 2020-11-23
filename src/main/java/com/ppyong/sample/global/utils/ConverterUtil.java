package com.ppyong.sample.global.utils;

import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ConverterUtil {
    private static final ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.getConfiguration().setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        modelMapper.getConfiguration().setFieldMatchingEnabled(true);
        modelMapper.addConverter(new StringOptionalConverter());
        modelMapper.addConverter(new LongOptionalConverter());
        modelMapper.addConverter(new IntegerOptionalConverter());
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

    private static class OptionalConverter<T> implements Converter<Optional, T>{
        @Override
        public T convert(MappingContext<Optional, T> context) {
            if(context.getSource() == null) {
                return context.getDestination();
            }
            if(context.getSource().isEmpty()){
                return null;
            }
            return (T)context.getSource().get();
        }
    }

    private static class StringOptionalConverter extends OptionalConverter<String> {}
    private static class LongOptionalConverter extends OptionalConverter<Long> {}
    private static class IntegerOptionalConverter extends OptionalConverter<Integer> {}
}
