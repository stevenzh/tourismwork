package com.opentravelsoft.providers;

import java.util.List;

import com.opentravelsoft.entity.Category;

public interface CategoryDao extends GenericDao<Category, Integer> {
  List<Category> getCategory();
}
