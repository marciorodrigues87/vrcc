package com.vrcc.infra.dao;

import java.util.Collection;

import com.vrcc.domain.Boundary;
import com.vrcc.domain.Province;

public interface ProvinceDAO {

	Collection<Province> find(Boundary boundary);

}
