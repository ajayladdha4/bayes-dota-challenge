package gg.bayes.challenge;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:application.yml")
@TestMethodOrder(OrderAnnotation.class)
public class DotaChallengeApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	private Integer matchIdCombat1 = 1;
	private Integer matchIdCombat2 = 2;

	@Test
	@Order(1) 
	void test_for_combat_1() throws Exception {

		Path path = Paths.get("data/combatlog_1.txt");
		byte[] data = Files.readAllBytes(path);

		MvcResult match_result = mockMvc
				.perform(post("/api/match").contentType(MediaType.TEXT_PLAIN_VALUE).content(data))
//            .andDo(print())
				.andExpect(status().isOk()).andReturn(); // jsonPath("$.customerId").value("1")

		String content = match_result.getResponse().getContentAsString();

		assertEquals(content, "1", "Checking payload id");
	}

	@Test
	@Order(2) 
	public void test_items_for_abyssal_underlord_combat_1() throws Exception {
		mockMvc.perform(get("/api/match/" + matchIdCombat1 + "/" + "abyssal_underlord" + "/items")
				.contentType(MediaType.APPLICATION_JSON))
         .andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].item", is("item_magic_stick")))
				.andExpect(jsonPath("$[0].timestamp", is(652229)));
//				.andReturn().getResponse().getContentAsString();
//		System.out.println(s);
	}

	@Test
	@Order(3) 
	public void test_spells_for_abyssal_underlord_combat_1() throws Exception {
		mockMvc.perform(get("/api/match/" + matchIdCombat1 + "/" + "abyssal_underlord" + "/spells")
				.contentType(MediaType.APPLICATION_JSON))
//         .andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].spell", is("abyssal_underlord_cancel_dark_rift")))
				.andExpect(jsonPath("$[0].casts", is(1))).andReturn(); //
	}

	@Test
	@Order(4) 
	public void test_damage_by_abyssal_underlord_combat_1() throws Exception {
		mockMvc.perform(get("/api/match/" + matchIdCombat1 + "/" + "abyssal_underlord" + "/damage")
				.contentType(MediaType.APPLICATION_JSON))
//         .andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].target", is("bane")))
				.andExpect(jsonPath("$[0].damage_instances", is(68))).andExpect(jsonPath("$[0].total_damage", is(3483)))
				.andReturn(); //
	}

	@Test
	@Order(5) 
	public void test_killds_by_abyssal_underlord_combat_1() throws Exception {
		mockMvc.perform(get("/api/match/" + matchIdCombat1).contentType(MediaType.APPLICATION_JSON))
				// .andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].hero", is("abyssal_underlord")))
				.andExpect(jsonPath("$[0].kills", is(6))).andReturn(); //
	}
	
	
	@Test
	@Order(6) 
	void test_for_combat_2() throws Exception {

		Path path = Paths.get("data/combatlog_2.txt");
		byte[] data = Files.readAllBytes(path);

		MvcResult match_result = mockMvc
				.perform(post("/api/match").contentType(MediaType.TEXT_PLAIN_VALUE).content(data))
//            .andDo(print())
				.andExpect(status().isOk()).andReturn(); // jsonPath("$.customerId").value("1")

		String content = match_result.getResponse().getContentAsString();

		assertEquals(content, "2", "Checking payload id");
	}

	@Test
	@Order(7) 
	public void test_items_for_centaur_combat_2() throws Exception {
		mockMvc.perform(get("/api/match/" + matchIdCombat2 + "/" + "centaur" + "/items")
				.contentType(MediaType.APPLICATION_JSON))
//         .andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].item", is("item_recipe_buckler")))
				.andExpect(jsonPath("$[0].timestamp", is(945190)));
//				.andReturn().getResponse().getContentAsString();
//		System.out.println(s);
	}

	@Test
	@Order(8) 
	public void test_spells_for_centaur_combat_2() throws Exception {
		mockMvc.perform(get("/api/match/" + matchIdCombat2 + "/" + "centaur" + "/spells")
				.contentType(MediaType.APPLICATION_JSON))
//         .andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].spell", is("centaur_double_edge")))
				.andExpect(jsonPath("$[0].casts", is(27))).andReturn(); //
	}

	@Test
	@Order(9) 
	public void test_damage_by_centaur_combat_2() throws Exception {
		mockMvc.perform(get("/api/match/" + matchIdCombat2 + "/" + "centaur" + "/damage")
				.contentType(MediaType.APPLICATION_JSON))
//         .andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].target", is("centaur")))
				.andExpect(jsonPath("$[0].damage_instances", is(27))).andExpect(jsonPath("$[0].total_damage", is(7015)))
				.andReturn(); //
	}

	@Test
	@Order(10) 
	public void test_killds_by_centaur_combat_2() throws Exception {
		mockMvc.perform(get("/api/match/" + matchIdCombat2).contentType(MediaType.APPLICATION_JSON))
				// .andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].hero", is("centaur")))
				.andExpect(jsonPath("$[0].kills", is(4))).andReturn(); //
	}

}
