package hello.item_service.web.basic;

import hello.item_service.domain.Item;
import hello.item_service.domain.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * packageName    : hello.item_service.web.basic
 * fileName       : BasicItemController
 * author         : mzc01-jungminim
 * date           : 2025. 4. 7.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025. 4. 7.        mzc01-jungminim       최초 생성
 */
@Slf4j
@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {

        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "/basic/addForm";
    }

//    @PostMapping("/add")
    public String saveRequestParam(
            @RequestParam("itemName") String itemName,
            @RequestParam("price") int price,
            @RequestParam("quantity") int quantity,
            Model model
    ) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute(item);

        return "/basic/item";
    }

//    @PostMapping("/add")
    public String saveModelAttirbuteV1(@ModelAttribute("item") Item item) {
        log.info("ModelAttribute >>> {}" , item);

        itemRepository.save(item);

        return "/basic/item";
    }

//    @PostMapping("/add")
    public String saveModelAttirbuteV2(@ModelAttribute Item item) {
        log.info("ModelAttribute >>> {}" , item);

        itemRepository.save(item);

        return "redirect:/basic/items/" + item.getId();
    }

    @PostMapping("/add")
    public String saveModelAttirbuteV3(@ModelAttribute Item item, RedirectAttributes redirectAttributes) {
        log.info("ModelAttribute >>> {}" , item);

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/basic/items/{itemId}";
    }

//    @PostMapping("/add")
    public String saveModelAttirbuteV3(Item item) {
        log.info("ModelAttribute >>> {}" , item);

        itemRepository.save(item);

        return "/basic/item";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        log.info("ModelAttribute >>> {}" , model);

        Item item = itemRepository.findById(itemId);

        model.addAttribute("item", item);

        return "/basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        log.info("ModelAttribute >>> {}" , item);

        itemRepository.update(itemId, item);

        return "redirect:/basic/items/{itemId}";
    }

    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA",10000,10));
        itemRepository.save(new Item("itemB",20000,20));
    }

}
