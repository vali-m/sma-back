package com.vali.sma_back.web.rest;

import com.vali.sma_back.SmaBackApp;

import com.vali.sma_back.domain.User1;
import com.vali.sma_back.repository.User1Repository;
import com.vali.sma_back.service.User1Service;
import com.vali.sma_back.service.dto.User1DTO;
import com.vali.sma_back.service.mapper.User1Mapper;
import com.vali.sma_back.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static com.vali.sma_back.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the User1Resource REST controller.
 *
 * @see User1Resource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SmaBackApp.class)
public class User1ResourceIntTest {

    private static final String DEFAULT_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_USERNAME = "BBBBBBBBBB";

    private static final Long DEFAULT_KARMA = 1L;
    private static final Long UPDATED_KARMA = 2L;

    @Autowired
    private User1Repository user1Repository;

    @Autowired
    private User1Mapper user1Mapper;

    @Autowired
    private User1Service user1Service;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restUser1MockMvc;

    private User1 user1;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final User1Resource user1Resource = new User1Resource(user1Service);
        this.restUser1MockMvc = MockMvcBuilders.standaloneSetup(user1Resource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static User1 createEntity(EntityManager em) {
        User1 user1 = new User1()
            .username(DEFAULT_USERNAME)
            .karma(DEFAULT_KARMA);
        return user1;
    }

    @Before
    public void initTest() {
        user1 = createEntity(em);
    }

    @Test
    @Transactional
    public void createUser1() throws Exception {
        int databaseSizeBeforeCreate = user1Repository.findAll().size();

        // Create the User1
        User1DTO user1DTO = user1Mapper.toDto(user1);
        restUser1MockMvc.perform(post("/api/user-1-s")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(user1DTO)))
            .andExpect(status().isCreated());

        // Validate the User1 in the database
        List<User1> user1List = user1Repository.findAll();
        assertThat(user1List).hasSize(databaseSizeBeforeCreate + 1);
        User1 testUser1 = user1List.get(user1List.size() - 1);
        assertThat(testUser1.getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(testUser1.getKarma()).isEqualTo(DEFAULT_KARMA);
    }

    @Test
    @Transactional
    public void createUser1WithExistingId() throws Exception {
        int databaseSizeBeforeCreate = user1Repository.findAll().size();

        // Create the User1 with an existing ID
        user1.setId(1L);
        User1DTO user1DTO = user1Mapper.toDto(user1);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUser1MockMvc.perform(post("/api/user-1-s")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(user1DTO)))
            .andExpect(status().isBadRequest());

        // Validate the User1 in the database
        List<User1> user1List = user1Repository.findAll();
        assertThat(user1List).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkUsernameIsRequired() throws Exception {
        int databaseSizeBeforeTest = user1Repository.findAll().size();
        // set the field null
        user1.setUsername(null);

        // Create the User1, which fails.
        User1DTO user1DTO = user1Mapper.toDto(user1);

        restUser1MockMvc.perform(post("/api/user-1-s")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(user1DTO)))
            .andExpect(status().isBadRequest());

        List<User1> user1List = user1Repository.findAll();
        assertThat(user1List).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKarmaIsRequired() throws Exception {
        int databaseSizeBeforeTest = user1Repository.findAll().size();
        // set the field null
        user1.setKarma(null);

        // Create the User1, which fails.
        User1DTO user1DTO = user1Mapper.toDto(user1);

        restUser1MockMvc.perform(post("/api/user-1-s")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(user1DTO)))
            .andExpect(status().isBadRequest());

        List<User1> user1List = user1Repository.findAll();
        assertThat(user1List).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUser1S() throws Exception {
        // Initialize the database
        user1Repository.saveAndFlush(user1);

        // Get all the user1List
        restUser1MockMvc.perform(get("/api/user-1-s?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(user1.getId().intValue())))
            .andExpect(jsonPath("$.[*].username").value(hasItem(DEFAULT_USERNAME.toString())))
            .andExpect(jsonPath("$.[*].karma").value(hasItem(DEFAULT_KARMA.intValue())));
    }
    
    @Test
    @Transactional
    public void getUser1() throws Exception {
        // Initialize the database
        user1Repository.saveAndFlush(user1);

        // Get the user1
        restUser1MockMvc.perform(get("/api/user-1-s/{id}", user1.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(user1.getId().intValue()))
            .andExpect(jsonPath("$.username").value(DEFAULT_USERNAME.toString()))
            .andExpect(jsonPath("$.karma").value(DEFAULT_KARMA.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingUser1() throws Exception {
        // Get the user1
        restUser1MockMvc.perform(get("/api/user-1-s/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUser1() throws Exception {
        // Initialize the database
        user1Repository.saveAndFlush(user1);

        int databaseSizeBeforeUpdate = user1Repository.findAll().size();

        // Update the user1
        User1 updatedUser1 = user1Repository.findById(user1.getId()).get();
        // Disconnect from session so that the updates on updatedUser1 are not directly saved in db
        em.detach(updatedUser1);
        updatedUser1
            .username(UPDATED_USERNAME)
            .karma(UPDATED_KARMA);
        User1DTO user1DTO = user1Mapper.toDto(updatedUser1);

        restUser1MockMvc.perform(put("/api/user-1-s")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(user1DTO)))
            .andExpect(status().isOk());

        // Validate the User1 in the database
        List<User1> user1List = user1Repository.findAll();
        assertThat(user1List).hasSize(databaseSizeBeforeUpdate);
        User1 testUser1 = user1List.get(user1List.size() - 1);
        assertThat(testUser1.getUsername()).isEqualTo(UPDATED_USERNAME);
        assertThat(testUser1.getKarma()).isEqualTo(UPDATED_KARMA);
    }

    @Test
    @Transactional
    public void updateNonExistingUser1() throws Exception {
        int databaseSizeBeforeUpdate = user1Repository.findAll().size();

        // Create the User1
        User1DTO user1DTO = user1Mapper.toDto(user1);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUser1MockMvc.perform(put("/api/user-1-s")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(user1DTO)))
            .andExpect(status().isBadRequest());

        // Validate the User1 in the database
        List<User1> user1List = user1Repository.findAll();
        assertThat(user1List).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUser1() throws Exception {
        // Initialize the database
        user1Repository.saveAndFlush(user1);

        int databaseSizeBeforeDelete = user1Repository.findAll().size();

        // Get the user1
        restUser1MockMvc.perform(delete("/api/user-1-s/{id}", user1.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<User1> user1List = user1Repository.findAll();
        assertThat(user1List).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(User1.class);
        User1 user11 = new User1();
        user11.setId(1L);
        User1 user12 = new User1();
        user12.setId(user11.getId());
        assertThat(user11).isEqualTo(user12);
        user12.setId(2L);
        assertThat(user11).isNotEqualTo(user12);
        user11.setId(null);
        assertThat(user11).isNotEqualTo(user12);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(User1DTO.class);
        User1DTO user1DTO1 = new User1DTO();
        user1DTO1.setId(1L);
        User1DTO user1DTO2 = new User1DTO();
        assertThat(user1DTO1).isNotEqualTo(user1DTO2);
        user1DTO2.setId(user1DTO1.getId());
        assertThat(user1DTO1).isEqualTo(user1DTO2);
        user1DTO2.setId(2L);
        assertThat(user1DTO1).isNotEqualTo(user1DTO2);
        user1DTO1.setId(null);
        assertThat(user1DTO1).isNotEqualTo(user1DTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(user1Mapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(user1Mapper.fromId(null)).isNull();
    }
}
