package com.bangbang.dto.bookmark;

import com.bangbang.domain.bookmark.Bookmark;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookmarkSaveRequestDto {

  private Long bookmarkId;
  private String bookmarkTitle;
  private String bookmarkComment;
  private Integer bookmarkItemType;
  private Integer bookmarkBuildingType;
  private Double bookmarkMinArea;
  private Double bookmarkMaxArea;
  private Integer bookmarkItemBuildMinYear;
  private Integer bookmarkItemBuildMaxYear;
  private Integer bookmarkItemMonthMinPrice;
  private Integer bookmarkItemMonthMaxPrice;
  private Integer bookmarkItemBuyMinPrice;
  private Integer bookmarkItemBuyMaxPrice;
  private Integer bookmarkItemMinDeposit;
  private Integer bookmarkItemMaxDeposit;
  private Long userId;
  private String dongCode;

  @Builder
  public BookmarkSaveRequestDto(String bookmarkTitle, String bookmarkComment, Integer bookmarkItemType, Double bookmarkMinArea, Double bookmarkMaxArea,
      Integer bookmarkBuildingType, String bookmarkItemBuildMinYear, String bookmarkItemBuildMaxYear,
      Integer bookmarkItemMonthMinPrice, Integer bookmarkItemMonthMaxPrice,
      Integer bookmarkItemBuyMinPrice, Integer bookmarkItemBuyMaxPrice,
      Integer bookmarkItemMinDeposit, Integer bookmarkItemMaxDeposit, Long userId, String dongCode){
    this.bookmarkTitle = bookmarkTitle;
    this.bookmarkComment = bookmarkComment;
    this.bookmarkItemType = bookmarkItemType;
    this.bookmarkBuildingType = bookmarkBuildingType;
    this.bookmarkMinArea = bookmarkMinArea;
    this.bookmarkMaxArea = bookmarkMaxArea;
    this.bookmarkItemBuildMinYear = Integer.parseInt(bookmarkItemBuildMinYear);
    this.bookmarkItemBuildMaxYear = Integer.parseInt(bookmarkItemBuildMaxYear);
    this.bookmarkItemMonthMinPrice = bookmarkItemMonthMinPrice;
    this.bookmarkItemMonthMaxPrice = bookmarkItemMonthMaxPrice;
    this.bookmarkItemBuyMinPrice = bookmarkItemBuyMinPrice;
    this.bookmarkItemBuyMaxPrice = bookmarkItemBuyMaxPrice;
    this.bookmarkItemMinDeposit = bookmarkItemMinDeposit;
    this.bookmarkItemMaxDeposit = bookmarkItemMaxDeposit;
    this.userId = userId;
    this.dongCode = dongCode;
  }

  public Bookmark toEntity(){
    return Bookmark.builder()
        .bookmarkTitle(bookmarkTitle)
        .bookmarkComment(bookmarkComment)
        .bookmarkItemType(bookmarkItemType)
        .bookmarkMinArea(bookmarkMinArea)
        .bookmarkMaxArea(bookmarkMaxArea)
        .bookmarkBuildingType(bookmarkBuildingType)
        .bookmarkItemBuildMinYear(bookmarkItemBuildMinYear)
        .bookmarkItemBuildMaxYear(bookmarkItemBuildMaxYear)
        .bookmarkItemMonthMinPrice(bookmarkItemMonthMinPrice)
        .bookmarkItemMonthMaxPrice(bookmarkItemMonthMaxPrice)
        .bookmarkItemBuyMinPrice(bookmarkItemBuyMinPrice)
        .bookmarkItemBuyMaxPrice(bookmarkItemBuyMaxPrice)
        .userId(userId)
        .dongcode(dongCode)
        .build();
  }

}
