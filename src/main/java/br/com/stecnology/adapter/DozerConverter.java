package br.com.stecnology.adapter;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class DozerConverter {

	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

	public static <Origem, Destination> Destination parseObject(Origem origin, Class<Destination> destination) {
		return mapper.map(origin, destination);
	}

	public static <Origem, Destination> List<Destination> parseListObjects(List<Origem> origin,
			Class<Destination> destination) {
		List<Destination> destinationObjects = new ArrayList<Destination>();
		for (Object ori : origin) {
			destinationObjects.add(mapper.map(ori, destination));

		}
		return destinationObjects;
	}
}
