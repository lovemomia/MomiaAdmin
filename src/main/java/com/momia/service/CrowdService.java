package com.momia.service;

import java.util.List;

import com.momia.entity.ArticleCrowd;
import com.momia.entity.Crowd;
import com.momia.entity.GoodsCrowd;
import com.momia.entity.TopicCrowd;

public interface CrowdService {

public Crowd findCrowdById(int id);
	
	public List<Crowd> findCrowds();
	public List<Crowd> findCrowds(List<Crowd> crowds,List<TopicCrowd> tcrowds);
	public List<Crowd> findGoodsCrowds(List<Crowd> crowds,List<GoodsCrowd> gCrowds);
	public List<Crowd> findArCrowds(List<Crowd> crowds,List<ArticleCrowd> arCrowds);
	

}
