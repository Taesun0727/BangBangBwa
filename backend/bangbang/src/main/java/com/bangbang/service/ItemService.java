package com.bangbang.service;

import com.bangbang.domain.item.Item;

import com.bangbang.domain.item.ItemPrice;
import com.bangbang.domain.item.ManageOption;
import com.bangbang.domain.item.Option;
import com.bangbang.dto.item.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ItemService {
    long newItem(ItemSaveRequestDto item);
    void newOption(OptionSaveRequestDto option, long item_id);
    void newManageOption(ManageOptionSaveRequestDto manageOption, long item_id);
    void newItemPrice(ItemPriceSaveRequestDto itemPrice, long item_id);
    Page<ItemDto> searchItemAll(Integer page, Integer size);
    List<ItemDto> searchSiGuDongAll(String dongCode);
    List<SidoDto> getSido();
    List<GugunDto> getGugunInSido(String sidoCode);
    List<DongDto> getDongInGugun(String gugunCode);
    SiGuDongDto getAddressName(String dongCode);
    ItemResponseDto itemDetail(long itemId);
    void deactivateItem(long itemId);
    void modifyItem(ItemUpdateDto item);
    void itemSold(long itemId);
    List<ItemDto> searchItemByFilter(ItemFilterRequestDto filter);
}