package vn.sapo.customer;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import vn.sapo.address.AddressService;
import vn.sapo.address.dto.AddressResult;
import vn.sapo.address.dto.CreateAddressParam;
import vn.sapo.controllers.customer.CustomerAPI;
import vn.sapo.customer.dto.*;
import vn.sapo.customerGroup.CustomerGroupService;
import vn.sapo.customerGroup.dto.CustomerGroupResult;
import vn.sapo.excel.ExcelService;
import vn.sapo.order.sale.SaleOrderService;
import vn.sapo.order.sale.item.OrderItemService;
import vn.sapo.payment.sale.PaymentSaleOrderService;
import vn.sapo.shared.parsers.JacksonParser;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebMvcTest(CustomerAPI.class)
public class CustomerAPITest {
    @MockBean
    private ModelMapper modelMapper;
    @MockBean
    private CustomerMapper customerMapper;
    @MockBean
    private ExcelService excelService;
    @MockBean
    private SaleOrderService saleOrderService;
    @MockBean
    private PaymentSaleOrderService paymentSaleOrderService;
    @MockBean
    private OrderItemService orderItemService;
    @MockBean
    private AddressService addressService;
    @MockBean
    private CustomerGroupService customerGroupService;
    @MockBean
    private CustomerService customerService;
    @Autowired
    private MockMvc mockMvc;
    private static CreateAddressParam createAddressParam;
    private static CreateCustomerParam createCustomerParam;
    private static List<AddressResult> addressResultList = new ArrayList<>();
    private static List<CustomerResult> customerResultList = new ArrayList<>();
    @BeforeAll
    static void init(){
        createAddressParam = new CreateAddressParam()
                                .setCustomerId(1)
                                .setFullName("trung")
                                .setPhoneNumber("089898989989")
                                .setEmail("trung@gmail.com")
                                .setSupplierId(1)
                                .setLine1("trrtrtrt")
                                .setLine2("fdssvsvs")
                                .setWardId(1)
                                .setWardName("fdfgfdgfgg")
                                .setDistrictId(2)
                                .setDistrictName("sdfsdfdsf")
                                .setProvinceId(3)
                                .setProvinceName("fdsfsdfsdfsd")
                                .setZipCode("dfsfsdfsdfsd")
                                .setShipping(true)
                                .setReceiveBill(false);
        createCustomerParam = new CreateCustomerParam()
                                    .setId(1)
                                    .setCustomerCode("dsdsdsdsdsd")
                                    .setCreateAddressParam(createAddressParam)
                                    .setFullName("trung")
                                    .setPhoneNumber("0809809809")
                                    .setDescription("fgfgdvdfgdgfd")
                                    .setGroupId(1)
                                    .setEmail("trung@gmail.com")
                                    .setWebsite("fgfgfg.com")
                                    .setFax("fsdfsdfsdfsdf")
                                    .setTaxCode("qeadqwdwq")
                                    .setBirthday(new Date())
                                    .setGender(CustomerGender.NAM)
                                    .setGroup(new CustomerGroupResult(1, "title", "fdfdfdf", 1, "fdsdfsdf",new Date(), 1L, "dsdsdsd", 2))
                                    .setEmployeeId(1)
                                    .setDebtTotal(new BigDecimal(1000))
                                    .setSpendTotal(new BigDecimal(1000))
                                    .setStatus(CustomerStatus.AVAILABLE);
        addressResultList.add(new AddressResult()
                .setId(1)
                .setPhoneNumber("089898989989")
                .setEmail("trung@gmail.com")
                .setSupplierId(1)
                .setLine1("trrtrtrt")
                .setLine2("fdssvsvs")
                .setWardId(1)
                .setWardName("fdfgfdgfgg")
                .setDistrictId(2)
                .setDistrictName("sdfsdfdsf")
                .setProvinceId(3)
                .setProvinceName("fdsfsdfsdfsd")
                .setZipCode("dfsfsdfsdfsd")
                .setShipping(true)
                .setReceiveBill(false));
        addressResultList.add(new AddressResult()
                .setId(1)
                .setPhoneNumber("089898989989")
                .setEmail("trung@gmail.com")
                .setSupplierId(1)
                .setLine1("trrtrtrt")
                .setLine2("fdssvsvs")
                .setWardId(1)
                .setWardName("fdfgfdgfgg")
                .setDistrictId(2)
                .setDistrictName("sdfsdfdsf")
                .setProvinceId(3)
                .setProvinceName("fdsfsdfsdfsd")
                .setZipCode("dfsfsdfsdfsd")
                .setShipping(true)
                .setReceiveBill(false));
        customerResultList.add(new CustomerResult(
                1,
                "rterterte",
                "trung",
                "0890809",
                "trug@gmail.com",
                Instant.now(),
                new CusGroupResult()
                        .setId(1)
                        .setDiscount(121212)
                        .setTitle("232323")
                        .setDescription("qeqeqeqw")
                        .setCusGrpCode("23213123123"),
                CustomerGender.NAM,
                "fsaasdada",
                new BigDecimal(1000),
                new BigDecimal(1000),
                CustomerStatus.AVAILABLE,
                Instant.now(),
                Instant.now(),
                new CusEmployeeResult()
                        .setId(1)
                        .setFullName("trung"),
                new AddressResult()
                        .setId(1)
                        .setPhoneNumber("089898989989")
                        .setEmail("trung@gmail.com")
                        .setSupplierId(1)
                        .setLine1("trrtrtrt")
                        .setLine2("fdssvsvs")
                        .setWardId(1)
                        .setWardName("fdfgfdgfgg")
                        .setDistrictId(2)
                        .setDistrictName("sdfsdfdsf")
                        .setProvinceId(3)
                        .setProvinceName("fdsfsdfsdfsd")
                        .setZipCode("dfsfsdfsdfsd")
                        .setShipping(true)
                        .setReceiveBill(false),
                new AddressResult()
                        .setId(1)
                        .setPhoneNumber("089898989989")
                        .setEmail("trung@gmail.com")
                        .setSupplierId(1)
                        .setLine1("trrtrtrt")
                        .setLine2("fdssvsvs")
                        .setWardId(1)
                        .setWardName("fdfgfdgfgg")
                        .setDistrictId(2)
                        .setDistrictName("sdfsdfdsf")
                        .setProvinceId(3)
                        .setProvinceName("fdsfsdfsdfsd")
                        .setZipCode("dfsfsdfsdfsd")
                        .setShipping(true)
                        .setReceiveBill(false),
                addressResultList,
                12,
                10,
                Instant.now(),
                new PaymentMethod()
                        .setId("dsdsds")
                        .setTitle("dsdsdsds"),
                "fsdfsdf",
                "sdfdsfsdf",
                "Fdserwerwe"
        ));
        customerResultList.add(new CustomerResult(
                2,
                "rterterte",
                "trung",
                "0890809",
                "trug@gmail.com",
                Instant.now(),
                new CusGroupResult()
                        .setId(1)
                        .setDiscount(121212)
                        .setTitle("232323")
                        .setDescription("qeqeqeqw")
                        .setCusGrpCode("23213123123"),
                CustomerGender.NAM,
                "fsaasdada",
                new BigDecimal(1000),
                new BigDecimal(1000),
                CustomerStatus.AVAILABLE,
                Instant.now(),
                Instant.now(),
                new CusEmployeeResult()
                        .setId(1)
                        .setFullName("trung"),
                new AddressResult()
                        .setId(1)
                        .setPhoneNumber("089898989989")
                        .setEmail("trung@gmail.com")
                        .setSupplierId(1)
                        .setLine1("trrtrtrt")
                        .setLine2("fdssvsvs")
                        .setWardId(1)
                        .setWardName("fdfgfdgfgg")
                        .setDistrictId(2)
                        .setDistrictName("sdfsdfdsf")
                        .setProvinceId(3)
                        .setProvinceName("fdsfsdfsdfsd")
                        .setZipCode("dfsfsdfsdfsd")
                        .setShipping(true)
                        .setReceiveBill(false),
                new AddressResult()
                        .setId(1)
                        .setPhoneNumber("089898989989")
                        .setEmail("trung@gmail.com")
                        .setSupplierId(1)
                        .setLine1("trrtrtrt")
                        .setLine2("fdssvsvs")
                        .setWardId(1)
                        .setWardName("fdfgfdgfgg")
                        .setDistrictId(2)
                        .setDistrictName("sdfsdfdsf")
                        .setProvinceId(3)
                        .setProvinceName("fdsfsdfsdfsd")
                        .setZipCode("dfsfsdfsdfsd")
                        .setShipping(true)
                        .setReceiveBill(false),
                addressResultList,
                12,
                10,
                Instant.now(),
                new PaymentMethod()
                        .setId("dsdsds")
                        .setTitle("dsdsdsds"),
                "fsdfsdf",
                "sdfdsfsdf",
                "Fdserwerwe"
        ));
    }
    @BeforeEach
    public void setUp(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        Mockito.when(customerService.findAll()).thenReturn(customerResultList);
        Mockito.when(customerService.findById(1)).thenReturn(customerResultList.get(0));
        Mockito.when(customerService.create(isA(CreateCustomerParam.class))).thenReturn(customerResultList.get(0));
        Mockito.when(addressService.create(isA(CreateAddressParam.class))).thenReturn(addressResultList.get(0));
    }
    @Test
    public void findAllCustomer() throws Exception {
        mockMvc.perform(get("/api/customers")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
    @Test
    public void findByCustomerById() throws Exception {
        mockMvc.perform(get("/api/customers/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }
    @Test
    public void createCustomer() throws Exception {
        mockMvc.perform(post("/api/customers/create")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(JacksonParser.INSTANCE.toJson(createCustomerParam))
                            .accept(MediaType.APPLICATION_JSON))
                            .andExpect(status().isCreated());
    }
}
