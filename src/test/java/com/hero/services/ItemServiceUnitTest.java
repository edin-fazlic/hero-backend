package com.hero.services;

import com.hero.data.ItemTest;
import com.hero.models.Item;
import com.hero.repositories.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class ItemServiceUnitTest {

    @MockBean
    private ItemRepository itemRepository;

    @TestConfiguration
    static class ItemServiceTestContextConfiguration {

        @Bean
        @Primary
        public ItemService itemService(ItemRepository itemRepository) {
            return new ItemService(itemRepository);
        }
    }

    @Autowired
    private ItemService itemService;

    @Test
    public void givenItems_whenGetItems_thenListShouldBeFound() {
        Mockito.when(itemRepository.findAll())
                .thenReturn(List.of(ItemTest.item()));

        List<Item> returnedList = itemService.getItems();

        assertThat(returnedList).hasSize(1);
    }

    @Test
    public void givenNoItems_whenGetItems_thenListShouldBeEmpty() {
        assertThat(itemService.getItems()).isEmpty();
    }

    @Test
    public void givenValidId_whenGetById_thenItemShouldBeFound() {
        Item item = ItemTest.item();

        Mockito.when(itemRepository.findById(item.getId()))
                .thenReturn(Optional.of(item));

        Item resultItem = itemService.getById(item.getId());

        assertThat(resultItem.getName())
                .isEqualTo(item.getName());
    }

    @Test
    public void givenInvalidId_whenGetById_thenExceptionShouldBeThrown() {
        assertThatThrownBy(() -> itemService.getById(2L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("does not exist");
    }

    @Test
    public void givenItem_whenCreate_thenIdAssigned() {
        Item inputItem = ItemTest.item();
        inputItem.setId(0L); // reset id
        Item outputItem = ItemTest.item();

        Mockito.when(itemRepository.save(inputItem))
                .thenReturn(outputItem);

        Item resultItem = itemService.create(inputItem);

        assertThat(resultItem.getId()).isNotEqualTo(0L);
    }

    @Test
    public void givenItem_whenCreate_thenItemReturned() {
        Item inputItem = ItemTest.item();
        inputItem.setId(0L); // reset id
        Item outputItem = ItemTest.item();

        Mockito.when(itemRepository.save(inputItem))
                .thenReturn(outputItem);

        Item resultItem = itemService.create(inputItem);

        assertThat(resultItem).isNotNull();
        assertThat(resultItem.getName()).isEqualTo(inputItem.getName());
    }

    @Test
    public void givenItem_whenCreate_thenRepositoryCalled() {
        Item item = ItemTest.item();

        itemService.create(item);

        verify(itemRepository, times(1)).save(item);
    }

    @Test
    public void givenItemAndValidId_whenUpdate_thenItemReturned() {
        Item inputItem = ItemTest.item();
        inputItem.setId(0L); // reset id
        long id = 2L;
        Item outputItem = ItemTest.item();
        outputItem.setId(id);

        Mockito.when(itemRepository.findById(id))
                .thenReturn(Optional.of(outputItem));
        Mockito.when(itemRepository.save(inputItem))
                .thenReturn(outputItem);

        Item resultItem = itemService.update(inputItem, id);

        assertThat(resultItem).isNotNull();
        assertThat(resultItem.getName()).isEqualTo(inputItem.getName());
        assertThat(resultItem.getId()).isEqualTo(id);
    }

    @Test
    public void givenInvalidId_whenUpdate_thenExceptionShouldBeThrown() {
        assertThatThrownBy(() -> itemService.update(ItemTest.item(), 2L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("does not exist");
    }

    @Test
    public void givenItem_whenDelete_thenRepositoryCalled() {
        long id = 2L;

        itemService.delete(id);

        verify(itemRepository, times(1)).deleteById(id);
    }

}
