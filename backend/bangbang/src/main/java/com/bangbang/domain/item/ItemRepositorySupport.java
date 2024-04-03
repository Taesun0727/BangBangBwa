package com.bangbang.domain.item;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;
    QItem qItem = QItem.item;

    public List<Item> findItemLimit20(Long id, Integer size) {
        List<Item> itemList = jpaQueryFactory
                .selectFrom(qItem)
                .join(qItem.option).fetchJoin()
                .join(qItem.itemPrice).fetchJoin()
                .join(qItem.manageOption).fetchJoin()
                .where(qItem.item_id.lt(id))
                .orderBy(qItem.item_id.desc())
                .limit(size+1)
                .fetch();

        return itemList;
    }

    public List<Item> findSearchItems(String sidoCode, String gugunCode, String dongCode) {

        List<Item> itemList = jpaQueryFactory
                .selectFrom(qItem)
                .join(qItem.option).fetchJoin()
                .join(qItem.itemPrice).fetchJoin()
                .join(qItem.manageOption).fetchJoin()
                .where(eqSidoCode(sidoCode), eqGugunCode(gugunCode), eqDongCode(dongCode))
                .orderBy(qItem.item_id.desc())
                .fetch();

        return itemList;
    }

    private BooleanExpression eqSidoCode(String sidoCode) {
        if (StringUtils.hasText(sidoCode)) {
            return qItem.item_sigungucode.eq(sidoCode);
        }
        return null;
    }

    private BooleanExpression eqGugunCode(String eqGugunCode) {
        if (StringUtils.hasText(eqGugunCode)) {
            return qItem.item_eubmyundongcode.eq(eqGugunCode);
        }
        return null;
    }

    private BooleanExpression eqDongCode(String eqDongCode) {
        if (StringUtils.hasText(eqDongCode)) {
            return qItem.item_dongcode.eq(eqDongCode);
        }
        return null;
    }
}
