package com.electronic.store.dtos;

import lombok.*;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PageableResponse<T> {

  private List<T> content;
  private int pageNo;
  private int pageSize;
  private long totalPages;
  private int totalElements;
  private boolean lastPage;


}
