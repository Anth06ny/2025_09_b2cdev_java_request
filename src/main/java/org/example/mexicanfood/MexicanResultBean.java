package org.example.mexicanfood;

import lombok.Data;

import java.util.List;

@Data
public class MexicanResultBean{
	private String difficulty;
	private String portion;
	private String description;
	private List<String> ingredients;
	private String id;
	private String time;
	private String title;
}