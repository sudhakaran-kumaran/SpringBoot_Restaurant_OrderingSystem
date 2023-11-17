package com.restapi.response;

import com.restapi.request.CategoryRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@ToString
public class CategoryResponse {
    private List<CategoryRequest> categories = new ArrayList<>();
}
