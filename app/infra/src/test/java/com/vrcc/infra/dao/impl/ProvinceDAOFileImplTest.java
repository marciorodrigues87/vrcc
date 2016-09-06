package com.vrcc.infra.dao.impl;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vrcc.domain.Boundary;
import com.vrcc.domain.Province;
import com.vrcc.utils.json.Jackson;

@RunWith(MockitoJUnitRunner.class)
public class ProvinceDAOFileImplTest {

	private ProvinceDAOFileImpl dao;

	@Mock
	private Boundary boundary;

	@Before
	public void before() {
		dao = new ProvinceDAOFileImpl(new Jackson(new ObjectMapper()));
	}

	@Test
	public void shouldFindProvince() {
		when(boundary.getX()).thenReturn(1);
		when(boundary.getY()).thenReturn(700);

		final Collection<Province> provinces = dao.find(boundary);

		assertEquals(1, provinces.size());
		assertEquals("Gode", provinces.iterator().next().getName());
	}

	@Test
	public void shoundFindProvincesBordersY() {
		when(boundary.getX()).thenReturn(1);
		when(boundary.getY()).thenReturn(500);

		final Collection<Province> provinces = dao.find(boundary);

		assertEquals(2, provinces.size());

		final List<String> provinceNames = provinces.stream().map(p -> p.getName()).collect(toList());

		Assert.assertTrue(provinceNames.contains("Gode"));
		Assert.assertTrue(provinceNames.contains("Scavy"));

	}

	@Test
	public void shoundFindProvincesBordersX() {
		when(boundary.getX()).thenReturn(1100);
		when(boundary.getY()).thenReturn(700);

		final Collection<Province> provinces = dao.find(boundary);

		assertEquals(2, provinces.size());

		final List<String> provinceNames = provinces.stream().map(p -> p.getName()).collect(toList());

		Assert.assertTrue(provinceNames.contains("Ruja"));
		Assert.assertTrue(provinceNames.contains("Jaby"));

	}

	@Test
	public void shouldFindProvincesOverlap() {
		when(boundary.getX()).thenReturn(500);
		when(boundary.getY()).thenReturn(700);

		final Collection<Province> provinces = dao.find(boundary);

		assertEquals(2, provinces.size());

		final List<String> provinceNames = provinces.stream().map(p -> p.getName()).collect(toList());

		Assert.assertTrue(provinceNames.contains("Ruja"));
		Assert.assertTrue(provinceNames.contains("Gode"));
	}

}
