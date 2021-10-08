package br.com.stecnology.adapter;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.stecnology.adapter.mocks.MockPerson;
import br.com.stecnology.data.dto.PersonDTO;
import br.com.stecnology.data.model.Person;
import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class DozerConverterTest {
	MockPerson inputObject;

	@Before
	public void setUp() {
		inputObject = new MockPerson();
	}

	@Test
	public void parseEntityToVOTest() {
		PersonDTO output = DozerConverter.parseObject(inputObject.mockEntity(), PersonDTO.class);
		Assert.assertEquals(Long.valueOf(0L), output.getKey());
		Assert.assertEquals("First Name Test0", output.getFirstName());
		Assert.assertEquals("Last Name Test0", output.getLastName());
		Assert.assertEquals("Male", output.getGender());
	}

	@Test
	public void parseEntityListToVOListTest() {
		List<PersonDTO> outputList = DozerConverter.parseListObjects(inputObject.mockEntityList(), PersonDTO.class);
		PersonDTO outputZero = outputList.get(0);

		Assert.assertEquals(Long.valueOf(0L), outputZero.getKey());
		Assert.assertEquals("First Name Test0", outputZero.getFirstName());
		Assert.assertEquals("Last Name Test0", outputZero.getLastName());
		Assert.assertEquals("Male", outputZero.getGender());

		PersonDTO outputSeven = outputList.get(7);

		Assert.assertEquals(Long.valueOf(7L), outputSeven.getKey());
		Assert.assertEquals("First Name Test7", outputSeven.getFirstName());
		Assert.assertEquals("Last Name Test7", outputSeven.getLastName());
		Assert.assertEquals("Female", outputSeven.getGender());

		PersonDTO outputTwelve = outputList.get(12);

		Assert.assertEquals(Long.valueOf(12L), outputTwelve.getKey());
		Assert.assertEquals("First Name Test12", outputTwelve.getFirstName());
		Assert.assertEquals("Last Name Test12", outputTwelve.getLastName());
		Assert.assertEquals("Male", outputTwelve.getGender());
	}

	@Test
	public void parseVOToEntityTest() {
		Person output = DozerConverter.parseObject(inputObject.mockDTO(), Person.class);
		Assert.assertEquals(Long.valueOf(0L), output.getId());
		Assert.assertEquals("First Name Test0", output.getFirstName());
		Assert.assertEquals("Last Name Test0", output.getLastName());
		Assert.assertEquals("Male", output.getGender());
	}

	@Test
	public void parserVOListToEntityListTest() {
		List<Person> outputList = DozerConverter.parseListObjects(inputObject.mockDTOList(), Person.class);
		Person outputZero = outputList.get(0);

		Assert.assertEquals(Long.valueOf(0L), outputZero.getId());
		Assert.assertEquals("First Name Test0", outputZero.getFirstName());
		Assert.assertEquals("Last Name Test0", outputZero.getLastName());
		Assert.assertEquals("Male", outputZero.getGender());

		Person outputSeven = outputList.get(7);

		Assert.assertEquals(Long.valueOf(7L), outputSeven.getId());
		Assert.assertEquals("First Name Test7", outputSeven.getFirstName());
		Assert.assertEquals("Last Name Test7", outputSeven.getLastName());
		Assert.assertEquals("Female", outputSeven.getGender());

		Person outputTwelve = outputList.get(12);

		Assert.assertEquals(Long.valueOf(12L), outputTwelve.getId());
		Assert.assertEquals("First Name Test12", outputTwelve.getFirstName());
		Assert.assertEquals("Last Name Test12", outputTwelve.getLastName());
		Assert.assertEquals("Male", outputTwelve.getGender());
	}
}