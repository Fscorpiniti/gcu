package com.edu.untref.gcu.daos;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.edu.untref.gcu.domain.Score;

@Repository("scoreDAO")
public class ScoreDAOImpl extends GenericDAOImpl<Score, Serializable> implements ScoreDAO {

	@Override
	protected Class<Score> getEntityClass() {
		return Score.class;
	}

}
