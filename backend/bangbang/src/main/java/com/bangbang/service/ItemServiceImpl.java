package com.bangbang.service;

import com.bangbang.domain.item.*;

import com.bangbang.dto.item.*;

import java.util.List;
import javax.transaction.Transactional;

import com.bangbang.domain.item.Item;
import com.bangbang.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    private final OptionRepository optionRepository;

    private final ManageOptionRepository manageOptionRepository;

    private final ItemPriceRepository itemPriceRepository;

    private final SidoCodeRepository sidoCodeRepository;

    private final GugunCodeRepository gugunCodeRepository;

    private final DongCodeRepository dongCodeRepository;

    private final ItemRepositorySupport itemRepositorySupport;

    @Transactional
    @Override
    public long newItem(ItemSaveRequestDto item) {
        return itemRepository.save(item.toEntity()).getItem_id();
    }

    @Transactional
    @Override
    public void newOption(OptionSaveRequestDto option, long item_id) {
        option.setItem_id(item_id);
        optionRepository.save(option.toEntity());
    }

    @Transactional
    @Override
    public void newManageOption(ManageOptionSaveRequestDto manageOption, long item_id) {
        manageOption.setItem_id(item_id);
        manageOptionRepository.save(manageOption.toEntity());
    }

    @Transactional
    @Override
    public void newItemPrice(ItemPriceSaveRequestDto itemPrice, long item_id) {
        itemPrice.setItem_id(item_id);
        itemPriceRepository.save(itemPrice.toEntity());
    }

//    @Override
//    public Page<Item> searchItemAll(Integer page, Integer size) {
//        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "item_id");
//        return itemRepository.findAllItem(pageable);
//    }

    @Override
    public List<Item> searchItemAll(long item_id, Integer size) {
        if (item_id == 0) item_id = Long.MAX_VALUE;

        return itemRepositorySupport.findItemLimit20(item_id, size);
    }

    @Override
    public List<ItemDto> searchSiGuDongAll(String dongCode) {
        return itemRepository.findByDongCode(dongCode);
    }

    @Override
    public List<SidoDto> getSido() {
        return sidoCodeRepository.getSido();
    }

    @Override
    public List<GugunDto> getGugunInSido(String sidoCode) {
        return gugunCodeRepository.getGugunInSido(sidoCode);
    }

    @Override
    public List<DongDto> getDongInGugun(String gugunCode) {
        return dongCodeRepository.getDongInGugun(gugunCode);
    }

    @Override
    public SiGuDongDto getAddressName(String dongCode) {
        return dongCodeRepository.getAddressName(dongCode);
    }

    @Override
    public ItemResponseDto itemDetail(long itemId) {
        Item item = itemRepository.findById(itemId);
        item.setManageOption(manageOptionRepository.findByItemId(itemId));
        item.setItemPrice(itemPriceRepository.findByItemId(itemId));
        item.setOption(optionRepository.findByItemId(itemId));
        return new ItemResponseDto(item);
    }

    @Transactional
    @Override
    public void deactivateItem(long itemId) {
        Item item =  itemRepository.findById(itemId);
        item.setItem_status(0);
        itemRepository.save(item);
    }

    @Transactional
    @Override
    public void modifyItem(ItemUpdateRequestDto item) {
        itemRepository.save(item.toEntity());
    }

    @Transactional
    @Override
    public void modifyOption(OptionUpdateRequestDto option) {
        optionRepository.save(option.toEntity());
    }

    @Transactional
    @Override
    public void modifyManageOption(ManageOptionUpdateRequestDto manageOption) {
        manageOptionRepository.save(manageOption.toEntity());
    }

    @Transactional
    @Override
    public void modifyItemPrice(ItemPriceUpdateRequestDto itemPrice) {
        itemPriceRepository.save(itemPrice.toEntity());
    }

    @Transactional
    @Override
    public void itemSold(long itemId) {
        Item item = itemRepository.findById(itemId);
        item.setItem_deal_complete(true);
        itemRepository.save(item);
    }

    @Autowired
    ItemQueryRepository itemQueryRepository;

    @Override
    public List<ItemDto> searchItemByFilter(ItemFilterRequestDto filter) {
        return itemQueryRepository.searchItemByFilter(filter);
    }
}