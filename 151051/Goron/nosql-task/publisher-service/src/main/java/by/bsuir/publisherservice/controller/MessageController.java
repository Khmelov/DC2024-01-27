package by.bsuir.publisherservice.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import by.bsuir.publisherservice.dto.request.MessageRequestTo;
import by.bsuir.publisherservice.dto.response.MessageResponseTo;
import by.bsuir.publisherservice.service.MessageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/messages")
public class MessageController extends by.bsuir.publisherservice.controller.RestController {

    private final MessageService MESSAGE_SERVICE;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseTo getById(@PathVariable Long id) {
        return MESSAGE_SERVICE.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MessageResponseTo> getAll(@RequestParam(name = "page", defaultValue = "0")
                                          Integer pageNumber,
                                          @RequestParam(name = "size", defaultValue = "5")
                                          Integer pageSize) {
        
        Pageable restriction = PageRequest.of(pageNumber, pageSize);

        return MESSAGE_SERVICE.getAll(restriction);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseTo save(@RequestBody @Valid MessageRequestTo message,
                                  HttpServletRequest request) {

        return MESSAGE_SERVICE.save(message, getCountry(request));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseTo update(@RequestBody @Valid MessageRequestTo message,
                                    HttpServletRequest request) {

        return MESSAGE_SERVICE.update(message, getCountry(request));
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        MESSAGE_SERVICE.delete(id);
    }

    private static String getCountry(HttpServletRequest request) {
        String requestCountry = request.getLocale().getDisplayCountry();
        return requestCountry.isEmpty() ? "Unspecified" : requestCountry;
    }

}
