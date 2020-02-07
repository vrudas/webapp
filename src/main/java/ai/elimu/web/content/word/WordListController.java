package ai.elimu.web.content.word;

import ai.elimu.dao.AllophoneDao;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import ai.elimu.dao.WordDao;
import ai.elimu.model.contributor.Contributor;
import ai.elimu.model.content.Allophone;
import ai.elimu.model.content.Word;
import ai.elimu.model.enums.Language;
import ai.elimu.web.content.number.NumberListController;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/content/word/list")
public class WordListController {
    
    private final Logger logger = Logger.getLogger(getClass());
    
    @Autowired
    private WordDao wordDao;
    
    @Autowired
    private AllophoneDao allophoneDao;

    @RequestMapping(method = RequestMethod.GET)
    public String handleRequest(Model model, HttpSession session) {
    	logger.info("handleRequest");
        
        Contributor contributor = (Contributor) session.getAttribute("contributor");
        
        // To ease development/testing, auto-generate Words
        List<Allophone> allophones = allophoneDao.readAllOrderedByUsage(contributor.getLanguage());
        List<Word> wordsGenerated = generateWords(contributor.getLanguage());
        for (Word word : wordsGenerated) {
            Word existingWord = wordDao.readByText(word.getLanguage(), word.getText());
            if (existingWord == null) {
                // Verify that only valid Allophones are used (copied from WordCreateController#handleSubmit)
                String allAllophonesCombined = "";
                for (Allophone allophone : allophones) {
                    allAllophonesCombined += allophone.getValueIpa();
                }
                if (StringUtils.isNotBlank(word.getPhonetics())) {
                    for (char allophoneCharacter : word.getPhonetics().toCharArray()) {
                        String allophone = String.valueOf(allophoneCharacter);
                        if (!allAllophonesCombined.contains(allophone)) {
                            throw new IllegalArgumentException("Invalid Allophone in the Word \"" + word.getText() + "\": /" + allophone + "/");
                        }
                    }
                }
                
                wordDao.create(word);
            }
        }
        
        List<Word> words = wordDao.readAllOrderedByUsage(contributor.getLanguage());
        model.addAttribute("words", words);
        
        int maxUsageCount = 0;
        for (Word word : words) {
            if (word.getUsageCount() > maxUsageCount) {
                maxUsageCount = word.getUsageCount();
            }
        }
        model.addAttribute("maxUsageCount", maxUsageCount);

        return "content/word/list";
    }
    
    /**
     * Note: these number words should be generated _before_ their corresponding numbers (see 
     * {@link NumberListController#generateNumbers(ai.elimu.model.enums.Language)}).
     */
    private List<Word> generateWords(Language language) {
        List<Word> words = new ArrayList<>();
        
        // Add number words

        Word wordZero = new Word();
        wordZero.setLanguage(language);
        wordZero.setTimeLastUpdate(Calendar.getInstance());
        if (language == Language.EN) {
            wordZero.setText("zero");
            wordZero.setPhonetics("ˈzɪrɔʊ");
        } else if (language == Language.FI) {
            wordZero.setText("sero");
            wordZero.setPhonetics("sɛr̩ɔ");
        } else if (language == Language.SW) {
            wordZero.setText("sifuri");
            wordZero.setPhonetics("siˈfuɾi");
        }
        words.add(wordZero);

        Word wordOne = new Word();
        wordOne.setLanguage(language);
        wordOne.setTimeLastUpdate(Calendar.getInstance());
        if (language == Language.EN) {
            wordOne.setText("one");
            wordOne.setPhonetics("wʌn");
        } else if (language == Language.FI) {
            wordOne.setText("isa");
            wordOne.setPhonetics("ɪsɑ");
        } else if (language == Language.SW) {
            wordOne.setText("moja");
            wordOne.setPhonetics("ˈmɔjɑ");
            wordOne.setUsageCount(29);
        }
        words.add(wordOne);

        Word wordTwo = new Word();
        wordTwo.setLanguage(language);
        wordTwo.setTimeLastUpdate(Calendar.getInstance());
        if (language == Language.EN) {
            wordTwo.setText("two");
            wordTwo.setPhonetics("tu");
        } else if (language == Language.FI) {
            wordTwo.setText("dalawa");
            wordTwo.setPhonetics("dɑlɑwɑ");
        } else if (language == Language.SW) {
            wordTwo.setText("mbili");
            wordTwo.setPhonetics("mˈbili");
        }
        words.add(wordTwo);

        Word wordThree = new Word();
        wordThree.setLanguage(language);
        wordThree.setTimeLastUpdate(Calendar.getInstance());
        if (language == Language.EN) {
            wordThree.setText("three");
            wordThree.setPhonetics("θri");
        } else if (language == Language.FI) {
            wordThree.setText("tatlo");
            wordThree.setPhonetics("tɑtlɔ");
        } else if (language == Language.SW) {
            wordThree.setText("tatu");
            wordThree.setPhonetics("ˈtɑtu");
            wordThree.setUsageCount(6);
        }
        words.add(wordThree);

        Word wordFour = new Word();
        wordFour.setLanguage(language);
        wordFour.setTimeLastUpdate(Calendar.getInstance());
        if (language == Language.EN) {
            wordFour.setText("four");
            wordFour.setPhonetics("fɔr");
        } else if (language == Language.FI) {
            wordFour.setText("apat");
            wordFour.setPhonetics("ɑpɑt");
        } else if (language == Language.SW) {
            wordFour.setText("nne");
            wordFour.setPhonetics("nˈnɛ");
        }
        words.add(wordFour);

        Word wordFive = new Word();
        wordFive.setLanguage(language);
        wordFive.setTimeLastUpdate(Calendar.getInstance());
        if (language == Language.EN) {
            wordFive.setText("five");
            wordFive.setPhonetics("fɑɪv");
        } else if (language == Language.FI) {
            wordFive.setText("lima");
            wordFive.setPhonetics("lɪmɑ");
        } else if (language == Language.SW) {
            wordFive.setText("tano");
            wordFive.setPhonetics("ˈtɑnɔ");
        }
        words.add(wordFive);

        Word wordSix = new Word();
        wordSix.setLanguage(language);
        wordSix.setTimeLastUpdate(Calendar.getInstance());
        if (language == Language.EN) {
            wordSix.setText("six");
            wordSix.setPhonetics("sɪks");
        } else if (language == Language.FI) {
            wordSix.setText("anim");
            wordSix.setPhonetics("ɑnɪm");
        } else if (language == Language.SW) {
            wordSix.setText("sita");
            wordSix.setPhonetics("ˈsitɑ");
            wordSix.setUsageCount(3);
        }
        words.add(wordSix);

        Word wordSeven = new Word();
        wordSeven.setLanguage(language);
        wordSeven.setTimeLastUpdate(Calendar.getInstance());
        if (language == Language.EN) {
            wordSeven.setText("seven");
            wordSeven.setPhonetics("ˈsɛvən");
        } else if (language == Language.FI) {
            wordSeven.setText("pito");
            wordSeven.setPhonetics("pɪtɔ");
        } else if (language == Language.SW) {
            wordSeven.setText("saba");
            wordSeven.setPhonetics("ˈsɑbɑ");
            wordSeven.setUsageCount(4);
        }
        words.add(wordSeven);

        Word wordEight = new Word();
        wordEight.setLanguage(language);
        wordEight.setTimeLastUpdate(Calendar.getInstance());
        if (language == Language.EN) {
            wordEight.setText("eight");
            wordEight.setPhonetics("ɛɪt");
        } else if (language == Language.FI) {
            wordEight.setText("walo");
            wordEight.setPhonetics("wɑlɔ");
        } else if (language == Language.SW) {
            wordEight.setText("nane");
            wordEight.setPhonetics("ˈnɑnɛ");
            wordEight.setUsageCount(1);
        }
        words.add(wordEight);

        Word wordNine = new Word();
        wordNine.setLanguage(language);
        wordNine.setTimeLastUpdate(Calendar.getInstance());
        if (language == Language.EN) {
            wordNine.setText("nine");
            wordNine.setPhonetics("nɑɪn");
        } else if (language == Language.FI) {
            wordNine.setText("siyam");
            wordNine.setPhonetics("ʃɑm");
        } else if (language == Language.SW) {
            wordNine.setText("tisa");
            wordNine.setPhonetics("ˈtisɑ");
        }
        words.add(wordNine);
        
        Word wordTen = new Word();
        wordTen.setLanguage(language);
        wordTen.setTimeLastUpdate(Calendar.getInstance());
        if (language == Language.EN) {
            wordTen.setText("ten");
            wordTen.setPhonetics("tɛn");
        } else if (language == Language.FI) {
            wordTen.setText("sampu");
            wordTen.setPhonetics("sɑmpu");
        } else if (language == Language.SW) {
            wordTen.setText("kumi");
            wordTen.setPhonetics("kumi");
            wordTen.setUsageCount(2);
        }
        words.add(wordTen);
        
        if (language == Language.SW) {
            // E.g. "kumi na mbili", which literally means "ten and two" (12)
            Word wordNa = new Word();
            wordNa.setLanguage(language);
            wordNa.setTimeLastUpdate(Calendar.getInstance());
            wordNa.setText("na");
            wordNa.setPhonetics("nɑ");
            wordNa.setUsageCount(179);
            words.add(wordNa);
        }
        
        if ((language == Language.EN) || (language == Language.FI)) {
            Word wordEleven = new Word();
            wordEleven.setLanguage(language);
            wordEleven.setTimeLastUpdate(Calendar.getInstance());
            if (language == Language.EN) {
                wordEleven.setText("eleven");
                wordEleven.setPhonetics("ɪˈlɛvən");
            } else if (language == Language.FI) {
                wordEleven.setText("labing-isa"); // TODO: handle "labing" and "isa" as separate words?
                wordEleven.setPhonetics("ˌˌˌ"); // TODO
            }
            words.add(wordEleven);

            Word wordTwelve = new Word();
            wordTwelve.setLanguage(language);
            wordTwelve.setTimeLastUpdate(Calendar.getInstance());
            if (language == Language.EN) {
                wordTwelve.setText("twelve");
                wordTwelve.setPhonetics("twɛlv");
            } else if (language == Language.FI) {
                wordTwelve.setText("labindalawa");
                wordTwelve.setPhonetics("ˌˌˌ"); // TODO
            }
            words.add(wordTwelve);

            Word wordThirteen = new Word();
            wordThirteen.setLanguage(language);
            wordThirteen.setTimeLastUpdate(Calendar.getInstance());
            if (language == Language.EN) {
                wordThirteen.setText("thirteen");
                wordThirteen.setPhonetics("θɛrtin");
            } else if (language == Language.FI) {
                wordThirteen.setText("labintatlo");
                wordThirteen.setPhonetics("ˌˌˌ"); // TODO
            }
            words.add(wordThirteen);

            Word wordFourteen = new Word();
            wordFourteen.setLanguage(language);
            wordFourteen.setTimeLastUpdate(Calendar.getInstance());
            if (language == Language.EN) {
                wordFourteen.setText("fourteen");
                wordFourteen.setPhonetics("ˈfɔrˈtin");
            } else if (language == Language.FI) {
                wordFourteen.setText("labing-apat");
                wordFourteen.setPhonetics("ˌˌˌ"); // TODO
            }
            words.add(wordFourteen);

            Word wordFifteen = new Word();
            wordFifteen.setLanguage(language);
            wordFifteen.setTimeLastUpdate(Calendar.getInstance());
            if (language == Language.EN) {
                wordFifteen.setText("fifteen");
                wordFifteen.setPhonetics("fɪfˈtin");
            } else if (language == Language.FI) {
                wordFifteen.setText("labinlima");
                wordFifteen.setPhonetics("ˌˌˌ"); // TODO
            }
            words.add(wordFifteen);

            Word wordSixteen = new Word();
            wordSixteen.setLanguage(language);
            wordSixteen.setTimeLastUpdate(Calendar.getInstance());
            if (language == Language.EN) {
                wordSixteen.setText("sixteen");
                wordSixteen.setPhonetics("sɪkˈstin");
            } else if (language == Language.FI) {
                wordSixteen.setText("labing-anim");
                wordSixteen.setPhonetics("ˌˌˌ"); // TODO
            }
            words.add(wordSixteen);

            Word wordSeventeen = new Word();
            wordSeventeen.setLanguage(language);
            wordSeventeen.setTimeLastUpdate(Calendar.getInstance());
            if (language == Language.EN) {
                wordSeventeen.setText("seventeen");
                wordSeventeen.setPhonetics("ˈsɛvənˈtin");
            } else if (language == Language.FI) {
                wordSeventeen.setText("labimpito");
                wordSeventeen.setPhonetics("ˌˌˌ"); // TODO
            }
            words.add(wordSeventeen);

            Word wordEighteen = new Word();
            wordEighteen.setLanguage(language);
            wordEighteen.setTimeLastUpdate(Calendar.getInstance());
            if (language == Language.EN) {
                wordEighteen.setText("eighteen");
                wordEighteen.setPhonetics("ɛɪtin");
            } else if (language == Language.FI) {
                wordEighteen.setText("labingwalo");
                wordEighteen.setPhonetics("ˌˌˌ"); // TODO
            }
            words.add(wordEighteen);

            Word wordNineteen = new Word();
            wordNineteen.setLanguage(language);
            wordNineteen.setTimeLastUpdate(Calendar.getInstance());
            if (language == Language.EN) {
                wordNineteen.setText("nineteen");
                wordNineteen.setPhonetics("ˈnaɪnˈtin");
            } else if (language == Language.FI) {
                wordNineteen.setText("labinsiyam");
                wordNineteen.setPhonetics("ˌˌˌ"); // TODO
            }
            words.add(wordNineteen);
        }
        
        Word wordTwenty = new Word();
        wordTwenty.setLanguage(language);
        wordTwenty.setTimeLastUpdate(Calendar.getInstance());
        if (language == Language.EN) {
            wordTwenty.setText("twenty");
            wordTwenty.setPhonetics("ˈtwɛnti");
        } else if (language == Language.FI) {
            wordTwenty.setText("dalawampu");
            wordTwenty.setPhonetics("ˌˌˌ"); // TODO
        } else if (language == Language.SW) {
            wordTwenty.setText("ishirini");
            wordTwenty.setPhonetics("iʃiɾini");
        }
        words.add(wordTwenty);
        
        if (language == Language.FI) {
            // "dalawampu't" = "dalawampu at", e.g. in "dalawampu't isa" (21)
            Word wordTwentyAnd = new Word();
            wordTwentyAnd.setLanguage(language);
            wordTwentyAnd.setTimeLastUpdate(Calendar.getInstance());
            wordTwentyAnd.setText("dalawampu't"); // TODO: handle apostrophe in "dalawampu't" ("dalawampu at")
            wordTwentyAnd.setPhonetics("ˌˌˌ"); // TODO
            words.add(wordTwentyAnd);
        }
        
        if (language == Language.EN) {
            Word wordTwentyOne = new Word();
            wordTwentyOne.setLanguage(language);
            wordTwentyOne.setTimeLastUpdate(Calendar.getInstance());
            wordTwentyOne.setText("twenty-one"); // TODO: handle words separated by hyphen
            wordTwentyOne.setPhonetics("ˈtwɛntiˈwʌn"); // TODO: handle pauses (whitespaces)
            words.add(wordTwentyOne);

            Word wordTwentyTwo = new Word();
            wordTwentyTwo.setLanguage(language);
            wordTwentyTwo.setTimeLastUpdate(Calendar.getInstance());
            wordTwentyTwo.setText("twenty-two"); // TODO: handle words separated by hyphen
            wordTwentyTwo.setPhonetics("ˈtwɛntiˈtu"); // TODO: handle pauses (whitespaces)
            words.add(wordTwentyTwo);
        }
        
        Word wordThirty = new Word();
        wordThirty.setLanguage(language);
        wordThirty.setTimeLastUpdate(Calendar.getInstance());
        if (language == Language.EN) {
            wordThirty.setText("thirty");
            wordThirty.setPhonetics("ˈθɝtɪ");
        } else if (language == Language.FI) {
            wordThirty.setText("tatlumpu");
            wordThirty.setPhonetics("ˌˌˌ"); // TODO
        } else if (language == Language.SW) {
            wordThirty.setText("thelathini");
            wordThirty.setPhonetics("θɛlɑθini");
        }
        words.add(wordThirty);
        
        // TODO: add 
        
        return words;
    }
}
