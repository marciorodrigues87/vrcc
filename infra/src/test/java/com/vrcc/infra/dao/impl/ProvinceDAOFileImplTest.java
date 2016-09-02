package com.vrcc.infra.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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
		dao = new ProvinceDAOFileImpl(new Jackson());
	}

	@Test
	public void shouldFindProvinces() {

		when(boundary.getX()).thenReturn(1);
		when(boundary.getY()).thenReturn(500);

		final Collection<Province> provinces = dao.find(boundary);

		assertEquals(1, provinces.size());

	}

}
