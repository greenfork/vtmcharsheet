(ns vtmcharsheet.data)

(def attribute-validations {5 0, 4 1, 3 3, 2 4, 1 1})
(def skill-validations
  {:jack-of-all-trades {5 0, 4 0, 3 1, 2 8, 1 10}
   :balanced {5 0, 4 0, 3 3, 2 5, 1 7}
   :specialist {5 0, 4 1, 3 3, 2 3, 1 3}})

(def numbers {0 "zero"
              1 "one"
              2 "two"
              3 "three"
              4 "four"
              5 "five"
              6 "six"
              7 "seven"
              8 "eight"
              9 "nine"
              10 "ten"})

(def clans
  (array-map
   :brujah
   {:name "Brujah"
    :description "Rebellious and short-tempered type."
    :archetypes
     [{:name "Cancer in the system"
       :description "Sabotage the system from within!"}
      {:name "Voice of the people"
       :description "I must speak for the people to resist the power!"}
      {:name "Blood worshipper"
       :description "Bow to my superiority or die in your ignorance!"}
      {:name "Trolling punk"
       :description "What are you looking at, got a problem?!"}
      {:name "Monster in disguise"
       :description "I'm still human and I can still live a healthy life!"}]
    :disciplines ["Celerity" "Potence" "Presence"]
    :bane "Short-tempered and explosive they take penalty on rolls to resist fury frenzy."}
   :gangrel
   {:name "Gangrel"
    :description "The beast within us is wild and free."
    :archetypes
    [{:name "Uncaged jailbird"
      :description "Finally free, I will take all the freedom I deserve."}
     {:name "Adventurer"
      :description "My home is sky, grass, trees, I live by the rules of nature."}
     {:name "Folk favorite"
      :description "Stories hold immense knowledge and knowledge is power."}
     {:name "Director of the board"
      :description "Assertive dominance is the only way to power in this world."}
     {:name "Unrepentant beast"
      :description "Everyone is a beast, embrace and live with it or die!"}]
    :disciplines ["Animalism" "Fortitude" "Protean"]
    :bane "Beast-like features emerge while in frenzy negatively affecting interactions."}
   :malkavian
   {:name "Malkavian"
    :description "The labyrinths of mind are our domain."
    :archetypes
    [{:name "Influencing presence"
      :description "Look at me, look at me! Do you like what you see?"}
     {:name "Medium"
      :description "One hears voices if one listens closely enough..."}
     {:name "Bad analyst"
      :description "I hear your thoughts, I taste your emotions, you are already mine!"}
     {:name "Fanatic"
      :description "You lie, you lie! I know the truth, just open your eyes!"}
     {:name "Pure blood addict"
      :description "I need to take this meal one more time, please, I'm so weak..."}]
    :disciplines ["Auspex" "Dominate" "Obfuscate"]
    :bane "Sometimes visions haunt you making it hard to function in this world"}
   :nosferatu
   {:name "Nosferatu"
    :description "Night is your friend, if you know what is hiding in it."
    :archetypes
    [{:name "Information hub"
      :description "Information is power, I have power, I know people."}
     {:name "Domain gumshoe"
      :description "Shadow and dark corners, help me uncover this mystery..."}
     {:name "More animal than man"
      :description "Pss-pss.. little rattie, come here, I'm your friend"}
     {:name "Hunter of monsters"
      :description "No one expects me to smash their skull, often their last mistake."}
     {:name "Rat"
      :description "People come, people go, but I survive everyone!.."}]
    :disciplines ["Animalism" "Obfuscate" "Potence"]
    :bane "Don't try to be a human, your monstrous nature is easily seen."}
   :toreador
   {:name "Toreador"
    :description "The art and beauty is as much a weapon as manipulation and deceit."
    :archetypes
    [{:name "l'Artiste"
      :description "Beauty and art is the music of this life, darling."}
     {:name "Stage manager"
      :description "The night is long and I will help it to be everyone's best!"}
     {:name "Gadabout"
      :description "Weak people are just instruments, smart people control the weak."}
     {:name "Patron of the arts"
      :description "A diamond needs polishing before it becomes a brilliant!"}
     {:name "Thespian spy"
      :description "Secrets and information is my art!"}]
    :disciplines ["Auspex" "Celerity" "Presence"]
    :bane "Beauty is addictive, you suffer when being in ugly surroundings."}
   :tremere
   {:name "Tremere"
    :description "Our art is of distilling and devouring the knowledge of this world."
    :archetypes
    [{:name "Pyramid loyalist"
      :description "Pyramid is the only way to our lost power."}
     {:name "Eternal scholar"
      :description "Knowledge is what makes everything work, only a fool doesn't have it."}
     {:name "Pagan nonconformist"
      :description "Traditions are old and bleak, the true knowledge is now."}
     {:name "Ambitious outsider"
      :description "My way is my way and I will follow it as I want."}
     {:name "Chief of security"
      :description "The sharp mind is what makes a perfect investigator."}]
    :disciplines ["Auspex" "Blood sorcery" "Dominate"]
    :bane "The blood once strong is now weak and doesn't bind mortals as well."}
   :ventrue
   {:name "Ventrue"
    :description "Power and money is what makes one great in this world."
    :archetypes
    [{:name "Cold-blooded corporate director"
      :description "Problems don't solve themselves."}
     {:name "Member of the order"
      :description "Respected inside a respectful circle."}
     {:name "Conservative politician"
      :description "Young are stupid, but I must be strong to lead this people."}
     {:name "Godfather"
      :description "My men is my hands."}
     {:name "High priest"
      :description "My ancestors will be proud of me!"}]
    :disciplines ["Dominate" "Fortitude" "Presence"]
    :bane "The blood you drink should be of highest quality."}
   :caitiff
   {:name "Caitiff"
    :description "A Kindred with no clan, a lost soul."
    :archetypes
    [{:name "Raised on the streets"
      :description "I know the rules of this life."}
     {:name "Abusive survivor"
      :description "Should I revenge all my abusers?"}
     {:name "Helplessly overestimated"
      :description "I have no idea what I am and what I should do."}
     {:name "Secret caitiff"
      :description "Everyone survives as one can, don't judje me."}
     {:name "Unwanted childe"
      :description "Never knew my sire, never wanted to know."}]
    :disciplines ["Any"]
    :bane "Not respected by Kindred and having increased cost for leveling skills."}
   :thin-blooded
   {:name "Thin-blooded"
    :description "Too weak for a true vampire, too strong for a true human."
    :archetypes
    [{:name "Live one"
      :description "Nothing changed, I will live my normal life as nothing happened."}
     {:name "Weapon of convenience"
      :description "My sire finally freed me of this miserable life."}
     {:name "Guilty embrace"
      :description "My sire saved me, or did they?"}
     {:name "Natural vampire"
      :description "I wanted to be a real vampire, not this weak image of a real power."}
     {:name "Redemption seeker"
      :description "They will never make me do what they want, revenge is coming."}]
    :disciplines ["Thin-blood alchemy"]
    :bane "None"}))

(def skill-distributions
  {:jack-of-all-trades
   {:name "Jack-of-all-trades"
    :description "Take one skill at 3; eight skills at 2; ten skills at 1."}
   :balanced
   {:name "Balanced"
    :description "Take three skills at 3; five skills at 2; seven skills at 1."}
   :specialist
   {:name "Specialist"
    :description "Take one skill at 4; three skills at 3; three skills at 2; three skills at 1."}})

(def attributes
  {:strength
   {:description "Strength governs how big a mortal you can lift, how hard you can hit them, and how much force you can push your dead body to exert."
    1 "You can easily crush a beer can (20kg: a Christmas tree, a stop sign)"
    2 "You are physically average (45kg: a toilet)"
    3 "You might be able to break open a wooden door (115kg: a large human, a refrigerator)"
    4 "You are a prime physical specimen, likely with very visible musculature (180kg: a full coffin, an empty dumpster)"
    5 "You are a true powerhouse and can likely break open a metal fire door, tear open a chain-link fence, or snap open a chained gate (250kg: a motorcycle, a piano)"}
   :dexterity
   {:description "Dexterity governs your agility and grace, how swiftly you dodge that stake to your heart, and how much fine motor control you possess when up against the clock."
    1 "You can run, but balance and dodging are a challenge"
    2 "You sprint is solid, and sometimes you appear graceful"
    3 "Your agility is impressive, and your coordination is as good as any trained amateur"
    4 "You could excel at acrobatics and move in a way few humans can"
    5 "Your movements are liquid and hypnotic - almost super-human"}
   :stamina
   {:description "Your physical resistance: Stamina absorbs physical harm, such as a speeding bullet or a hunter's blade, and lets you persevere through hazards and arduous effort."
    1 "Even lesser exertions make you winded"
    2 "You can take a beating, but consider suing for peace"
    3 "Several days of hard hiking with a backpack is no problem for you"
    4 "You could win a marathon or take copious amounts of pain, at least physically"
    5 "Even if you were a mortal, you'd never break a sweat"}
   :charisma
   {:description "Charisma measures your natural charm, grace, and sex appeal. If you have it, it draws people to you, making feeding a hell of a lot easier. Charisma doesn't depend on good looks, which are their own merit."
    1 "You can speak clearly, though few people tend to listen"
    2 "Generally likeable despite your undead nature, you may even have friends"
    3 "People trust you implicitly, and you easily make friends"
    4 "You possess significant personal mgnetism and draw followers like flies"
    5 "You could lead a city in rebellion, if you so choose"}
   :manipulation
   {:description "Manipulation is your ability to twist others to your point of view, lie convincingly, and walk away after duping a mark without anyone being any the wiser."
    1 "As long as you stay honest, you can convince people to do what you want"
    2 "Your ability to deceive surpasses the will of the weak-willed and simple-minded"
    3 "You never have to pay full price for anything"
    4 "You could be a cult leader - or a politician"
    5 "You could convince the Prince to invest in desert property, or maybe even to call of the Blood Hunt on your head"}
   :composure
   {:description "Composure allows you to remain calm, to command your emotions, and to put others at ease despite anxiety. It is also represents your ability to stay cool in everything from firefights to intimate encounters."
    1 "The slightest insult or confrontation could drive you to frenzy"
    2 "You can subdue your predatory instincts in most non-hostile situations"
    3 "Others look to you for guidance when the blood spatter hits the fan"
    4 "You can effortlessly bluff at cards and can manage your Beast to some extent"
    5 "The Beast is your pet"}
   :intelligence
   {:description "Intelligence measures your ability to reason, research, and apply logic. You can recall an analyze information from books or from your senses. No puzzle or mystery can elude the truly intelligent."
    1 "You can read and write competently, though some terms confound you"
    2 "You are smart enough to realize your limitations"
    3 "You are enlightened, able to piece together clues without difficulty"
    4 "You're likely consulted by members of Clan Tremere for your wisdom"
    5 "Genius does not cover the depths and range of your intellect"}
   :wits
   {:description "Wits are for thinking quickly and reacting correctly on little information. \"You hear a sound\" is Wits; \"You hear two guards coming\" is Intelligence. Wits let you smell an ambush or answer the Harpy back at court right away, instead of thinking of the best response the next night."
    1 "You get the point eventually, but it takes explaining"
    2 "You can bet the odds in poker or apply the emergency brakes in time, usually"
    3 "You can analyze a situation and quickly work out the best escape route"
    4 "You are never caught on the back foot and always come up with a smart riposte"
    5 "You think and respond more quickly than most people can comprehend"}
   :resolve
   {:description "Resolve provides focus and determination, and measures concentration and mental fortitude. Resolve powers all-night watches and blocks out distractions."
    1 "You have minimal attention for all but the most pressing things"
    2 "You can settle in for the long haul, as long as it's not too long"
    3 "Distracting you takes more effort than most other people want to spend"
    4 "You can brute-force your way to a deduction past any obstacles"
    5 "You can think in a gunfight or watch the door in a blood orgy and then clean up every shell casing or spilled droplet"}})

(def skills
  {:athletics
   {:description "Athletics allows you to outpace someone in pursuit, leap out of the way of an oncoming car, and climb and swim like a healthy, robust living person."
    1 "You were always attentive in gym class and you still have a spring in your step"
    2 "Despite being dead, you are still as fit as a mortal doing regular exercise"
    3 "You are fit as a fiddle and could play professional sports"
    4 "With your parkour abilities, why would you need to turn into a bat?"
    5 "Olympic records await you; only a very few peak humans can achive what you can"
    :specialties ["Acrobatics" "Archery" "Climbing" "Endurance" "Jumping" "Parkour"
                  "Swimming" "Throwing"]}
   :brawl
   {:description "Brawl enables characters to hit their target when they swing with fist, boot, or claw."
    1 "You had a tough upbringing and had to fight to justify your place; you still have some moves"
    2 "You received some training in hitting someone hard and accurately"
    3 "You more than hold your own in a scrap"
    4 "You either received Spetsnaz-quality training, or you have spend decades of afterlife in fights"
    5 "You could win MMA championships even without the use of your vampiric powers"
    :specialties ["Animals" "Armed mortals" "Bar fights" "Grappling" "Kindred"
                  "Sporting combat" "Unarmed mortals" "Werewolves"
                  "While in Protean Beast form"]}
   :craft
   {:description "Craft broadly encompasses artistry, creation of itmes and utilities from the beautiful to the functional, and arts and crafts from throwing pottery all the way to building and reinforcing your own haven."
    1 "You are an amateur, but you know what you are doing"
    2 "Your craftsmanship is admired for its functionality"
    3 "Your creations can be beautiful or horrifying, but their intent always clear"
    4 "Your skill is highly respected among the kine and Kindred aware of it"
    5 "You are often chosen to create the focal point for parties at Elysium"
    :specialties ["Carpentry" "Carving" "Design" "Painting" "Sculpting" "Sewing"
                  "Weaponsmithing"]}
   :drive
   {:description "Anyone can learn to drive a car. The drive skill connotes ability to drive fast and safely under adverse conditions or in stressful situations: to drive off-road, speed away from ambushes, win street races, and get out of chases with the Second Inquisition."
    1 "You are a cautious driver, unlikely to make any mistakes"
    2 "You can put your foot down without much fear of an accident, providing visibility is good"
    3 "You have won car chases, earning a cool reputation among the Anarchs"
    4 "You could be a stunt driver or the personal chauffeur of a Prince of Baron"
    5 "You know cars inside and our; few can match your skill and knowledge"
    :specialties ["All-terrain vehicles" "Evasion" "Motorcycles" "Street racing"
                  "Stunts" "Tailing" "Trucks" "Vintage models"]}
   :firearms
   {:description "Cainites use firearms not only for the human reasons but to preserve the Masquerade. This skill comprises familiarity with small arms from holdout pistols to assault rifles. It also includes other trigger-operated weapons, such as crossbows and shoulder-fired rocket-propelled grenades. Finally, it includes cleaning, unjamming, and rapidly reloading such weapons."
    1 "You've fired a gun a few times, at the range or in less-formal circumstances"
    2 "You know to keep your weapon clean, take it apart, and reassemble it"
    3 "You've been in the shit and come out the other side"
    4 "You can handle trick shots, called shots, running shots - really anything ending in a gunshot"
    5 "You've been practicing since the debut of the Winchester"
    :specialties ["Crossbows" "Gun dealing" "Gunsmithing" "Handloading ammunition"
                  "Quick-draw" "Sniper" "Trick shooting"]}
   :larceny
   {:description "Larceny entails familiarity with the tools and techniques for picking locks, planting bugs, deactivating standard burglar and car alarms, manual forgery, hot-wiring automobiles, or even safecracking, as well as countless forms of breaking and enering."
    1 "You can pick a simple lock or a careless pocket"
    2 "You can hotwire a car or shoplift with ease"
    3 "You can identify the locations of security cameras and alarms so to best bypass them"
    4 "You can defeat a keypad, re-tool and ID card, or crack a safe"
    5 "You can get into - or out of - the vault of a multinational bank"
    :specialties ["Alarms" "Forgery" "Grand theft auto" "Housebreaking" "Lockpicking"
                  "Pickpocket" "Safecracking" "Security analysis"]}
   :melee
   {:description "Use melee to wield handheld weapons such as knives, chains, and baseball bats with prowess. A stake is a melee weapon, often found in the hands of would-be hunters."
    1 "You can swing a bat or blade and mostly hit the people you intend to"
    2 "Your clear competence with a weapon in hand should give attackers pause"
    3 "Your skill with a melee weapon is known throughout the domain"
    4 "The fools brought a gun to your knife fight"
    5 "You are the domain's weaponmaster, sought out by Kindred far and wide for your skill"
    :specialties ["Axes" "Chains" "Clubs" "Fencing" "Disarming blows" "Garrotes"
                  "Improvised weapons" "Knives" "Stakes" "Swords"]}
   :stealth
   {:description "Stealth allows a character to shadow a target, making vampires with this ability superlative hunters. They benefit from the ability to spy, sneak, and blend in with crowds when needed."
    1 "Spotting you under the cover of darkness or in camouflage proves difficult"
    2 "You can sneak by casual observers and stalk unknowing victims without raising any hackles"
    3 "You evade patrolling guards, moving softly and hiding easily"
    4 "Your subtle, silent passage could make you a worthy ninja - or a worthy foe for ninja"
    5 "The Children of Haqim come to you for advice on stalking and hiding, if the can find you"
    :specialties ["Ambushes" "Crowds" "Disguise" "Hiding" "Shadowing"
                  "Silent movement" "Urban" "Wilderness"]}
   :survival
   {:description "Survival conveys the ability to exist in the wild and other adverse conditions, and to return to civilization again: navigate by the starts, establish a makeshift haven, and notice werewolf signs before it's too late. Some of its related functions apply in parks, brownfields, and other wastelands of the urban junle."
    1 "You know the trails and wilderness around your domain"
    2 "You spend more time ourdoors than inside, and you can track anyone who doesn't possess woodcraft"
    3 "You can subsist outside the city, setting traps for mortals and a shelter for yourself"
    4 "You can thrive outside the city like the true predator you are"
    5 "Gangrel run in packs with you - if they can keep up"
    :specialties ["Desert" "Hunting" "Jungle" "Tracking" "Traps" "Shelters"
                  "Urban explorations" "Woodlands"]}
   :animal-ken
   {:description "Animal ken allows you to cow, pacify, and even befriend animals. This skill allows you to predict how an animal might react in a given situation, train a domesticated creature, or even try to calm or enrage animals. Without this skill, most creatures pointedly avoid or grow aggressive around vampiers."
    1 "Animals shy away from you but will not bolt or snap"
    2 "Animals are docile around you, acting as if you aren't there, unless you build a rapport with them"
    3 "Animals treat you as if you were a warm, friendly owner, unless provoked to do otherwise"
    4 "You draw animals into your orbit; few attacking creatures maintain their aggression"
    5 "You can sense an animal's feelings and thoughts, and they can feel and act on yours"
    :specialties ["Attack training" "Cats" "Dogs" "Falconry" "Horses" "Pacification"
                  "Rats" "Snakes" "Stunt training" "Wolves"]}
   :etiquette
   {:description "Etiquette is the ability to identify and respond to the social conventions in the current scene, set new protocols, and please everyone around with your good graces."
    1 "You know how to address your local ruler without causing a faux pas"
    2 "You know the rules of etiquette in each of the domain's popular nighspots"
    3 "You impress others with your command of politeness, deference, and grace"
    4 "Your behavior sets trends, especially if you do something to buck the norm of etiquette"
    5 "The Keeper of Elysium and the Harpies look to you to establish domain protocol"
    :specialties ["Anarch" "Camarilla" "Celebrities" "Corporate" "Elysium"
                  "Feudal" "One-percenter" "Secret society"]}
   :insight
   {:description "Insight grants you the ability to interpret body language, note subtle cues in expression and tone, and determine truth from lies. It also allows you to perceive and understand the motives behind another's actions."
    1 "You can see straight through hollow boasts"
    2 "You pick up on the hidden emotion between humans and even sometimes between Kindred"
    3 "You can provide psychotherapy even more effectivly than someone who doesn't want to eat their patient"
    4 "You are an undead lie detector; only the best con artists can slip one by you"
    5 "People may be books of blood, but they are printed in very large type - easy to read"
    :specialties ["Ambitions" "Desires" "Detect lies" "Emotions" "Empathy"
                  "Interrogation" "Motives" "Phobias" "Vices"]}
   :intimidation
   {:description "Intimidation is the power to bully, coerce, threaten, and strongarm your way to a social victory. Vampires who rely on intimidation do not hesitate to crush the wills - and occasionally the finger bones - of their foes."
    1 "You can cut a scathing insult"
    2 "You can push your way around most humans without difficulty"
    3 "Your swagger and your cruel behavior have earned you your reputation"
    4 "You're far past mere physical threats"
    5 "Even fellow Kindred may step back if you step up"
    :specialties ["Extortion" "Insults" "Interrogation" "Physical coercion"
                  "Staredowns" "Veiled threats"]}
   :leadership
   {:description "Leadership grants you the ability to lead a crowd, command a detachment, boost the morale of your supporters, or subdue a riot. A strong Prince or Baron must possess leadership or risk losing their throne."
    1 "You have led informal clubs before and can organize Kindred of like mind"
    2 "Your voice makes itself heard in council, and even your superiors sometimes listen"
    3 "You can command on the battlefield and lead your soldiers into death"
    4 "You can inspire the injured and dying to action, so long as you are there to lead them"
    5 "Your words fill a vampire's heart with enough strength to make it feel alive and beating"
    :specialties ["Command" "Inspiration" "Oratory" "Praxis" "Team dynamies"
                  "War pack"]}
   :performance
   {:description "Performance covers a range of arts, from dance to poetry and comedy to storytelling. You may be an inspired performer in your own right or simply an enthusiastic student of the arts."
    1 "You are the life of the party, but you wouldn't take your act on stage"
    2 "You have performed for others, to mixed reviews; some love you, others hate you"
    3 "You are an expert student of your chosen performing art"
    4 "You perform your practiced art from stunningly, winning over even Toreador far and wide"
    5 "Improvisation holds no terrors for you: every night a different audience, every night a different show"
    :specialties ["Comedy" "Dance" "Drama" "Drums" "Guitar" "Keyboards" "Poetry"
                  "Public speaking" "Rap" "Singing" "Violin" "Wind instuments"]}
   :persuasion
   {:description "Use persuasion when trying to convince others that you know what's best for them, and that one bit won't hurt at all. Skilled persuaders can play on victims' emotions and appeal to peers' reason. Persuasion applies in law courts and princely courts, in boardrooms, barrooms, and bedrooms"
    1 "You can sell to a motivated buyer"
    2 "You can always swing a discount or get on the inside track of the latest gossip"
    3 "You can always figure out a compromise in a given conflict"
    4 "The other side starts looking for a settlement when they see you in court, human or Kindred"
    5 "You may be the original silver-tongued devil"
    :specialties ["Bargaining" "Fast talk" "Interrogation" "Legal argument"
                  "Negotiation" "Rhetoric"]}
   :streetwise
   {:description "Streetwise enables characters to talk the language and negotiate the societies of the streets and the underworld. You can understand code words and slang, interpret graffiti tags, and emulate gang signs."
    1 "You know where to score drugs and sex in your domain"
    2 "You know which gangs operate in the area, including their colors and rivalries; you may have your own graffiti tag"
    3 "You can tell the good shit from bad, score guns, and blend in seamlessly with street people or gangbangers"
    4 "When gangsters say \"I know a guy\", you're who they know"
    5 "You can hire, orchestrate, or arrange almost any criminal activity anywhere in your city"
    :specialties ["Arms dealing" "Black market" "Bribery" "Drugs" "Fence stolen goods"
                  "Gangs" "Graffiti" "Personal rep" "Sex trade" "Urban survival"]}
   :subterfuge
   {:description "Subterfuge is the art of lying convincingly, spinning a yarn, and making good excuses for bad actions. This skill defines your talent for intigue, secrets, and double-dealing. Subterfuge can also used for seduction and imitating mortal behavior."
    1 "You can tell believable, simple lies"
    2 "You can hustle naive people, old and young, into giving you their things"
    3 "You can operate on multiple lvels, telling lies intended to be uncovered to bolster other lies"
    4 "You can operate endlessly in deep cover: a perfect double agent"
    5 "Nobody believes you have even a single dot in subterfuge"
    :specialties ["Bluff" "Feign mortality" "Impeccable lies" "Innocence"
                  "The long con" "Seduction"]}
   :academics
   {:description "Academics reflects understanding, higher education, and ability to research fields within the humanities and the liberal arts. Historical study, for instance, is hardly \"just academic\" if your immortal enemies lived - and left traces - in that period."
    1 "Basic primary and secondary education; a night class at community college"
    2 "Basic university education or tutelage from a mediocre mentor; a four-year degree"
    3 "Advanced university education or dedicated personal tuition; an excellen four-year degree, a doctorate"
    4 "Advanced specialized study beyond university, learning into subjects few understand"
    5 "Refined and advanced scholarship, likely to be sought out for advice and tutelage"
    :specialties ["Architecture" "English literature" "History of art"
                  "History (specific field or period)" "Journalism" "Philosophy"
                  "Research" "Teaching" "Theology"]}
   :awareness
   {:description "Awareness handles your perceptions. You may spot a Child of Haqim before a strike occurs, spy a key dropped in the trash, or sense a lingering perfume."
    1 "You have a history of knowing when something is out of place"
    2 "You can spot erratic or pattern behavior in an individual"
    3 "You can see through most disguises and sense concealed dangers or hidden clues"
    4 "Even when you are distracted, few things are beyond your notice"
    5 "Your senses are those of a wild animal"
    :specialties ["Ambushes" "Camouflage" "Concealed objects" "Hearing" "Instincts"
                  "Smell" "Sight" "Traps" "Wilderness"]}
   :finance
   {:description "Finance allows you to identify trends in the market, invest well, manipulate stock, and know when a fall is coming. It also allows you to asses - and trace - the wealth of others and broker financial deals. You can generally appraise art, property, or any other non-criminal good. Ventrue value this skill more than some disciplines."
    1 "You can run a business and keep books"
    2 "You can manage a corporate division or bank branch; you file impeccably plausible tax returns"
    3 "Thanks to international trading, you make a fine broker on foreign stock exchanges"
    4 "Investment banks follow your financial lead; you have no trouble concealing fraud"
    5 "You can make money do anything, including stick to your pockets - or crash countries"
    :specialties ["Appraisal" "Banking" "Black markets" "Corporate finance"
                  "Currency manipulation" "Fine art" "Forensic accounting"
                  "Money laundering" "Stock market"]}
   :investigation
   {:description "Investigation allows you to unravel cases of mundane and mysterious means, spot clues, interpret them, and trace missing persons. Vampires find this skill especially useful when a vessel escapes."
    1 "You love a good mystery novel and fancy yourself an amateur sleuth"
    2 "You have a firm knowledge of criminology and the signature acts of local rogues"
    3 "You are, or could be, a detective by trade; nothing in a crime scene escapes you"
    4 "The Sheriff comes to you when unknown parties sabotage the domain's safety"
    5 "You set riddles for others and live an enigmatic existence few can penetrate"
    :specialties ["Criminology" "Deduction" "Forensics" "Missing persons"
                  "Murder" "Paranormal mysteries" "Traffic analysis"]}
   :medicine
   {:description "Medicine allows you to fix people who are broken and diagnose causes of death or sickness in a victim. It also allows you to use medical equipment, prescribe drugs, and stanch (or encourage) rapid blood flow."
    1 "You know basic anatomy and the difference in blood flow from a vein and an artery; you can perform CPR and other first aid; you may have been a medical student in life"
    2 "You can comfortably treat minor traumas and illnesses and narrow down a diagnosis; you may have been a nurse or EMT in life"
    3 "Your training allows you to perform major operations and treat serious injuries; you may have been an internist, pediatrician, or general practitioner in life"
    4 "You can diagnose and treat all but the rarest of illnesses; you may have been a surgeon or specialist in life"
    5 "You are a noted medical expert, sought out by mortals and immortals alike"
    :specialties ["First aid" "Haematoloty" "Pathology" "Pharmacy" "Phlebotomy"
                  "Surgery" "Trauma care" "Veterinary"]}
   :occult
   {:description "Occult represents knowledge of the mystical world, extending from the rites and practices of Freemasons and Rosicrucians, all the way to Noddist scholars and real mages. You can recognize occult sigils and folk-magic practices, effective or not."
    1 "You know the legends of Caine and the Antediluvians, and you may have read the Book of Nod"
    2 "You can sift the truth from pop occult nonsense"
    3 "You have firsthand experience of something inexplicable, even by Kindred standards"
    4 "You could name most of the Antediluvians and even comprehend a Tremere ritual"
    5 "Tremere and Children of Haqim consult with you on obscure lore"
    :specialties ["Alchemy" "Blood magic" "Faeries" "Ghosts" "Grimoires"
                  "Infernalism" "Magi" "Necromancy" "Noddism" "Parapsychology"
                  "Voudun" "Werewolves"]}
   :politics
   {:description "Politics diplomacy and bureaucracy: both human and Kindred. You can work with, and possibly put pressure on, city government and perhaps higher levels. Among Kindred, you know the inside scoop on which sects are dominant where, who is at war with whom, and where the bodies are buried. Literally."
    1 "You fllow mortal political affairs in you regnum, and you know at least what the elders reveal about Kindred politics"
    2 "You can apply influence at a local level, or you know who can"
    3 "You could run political campaigns or political machines, or make waves in your sect as an up-and-comer"
    4 "You know the true personalities of the real movers and shakers, live and undead, in your area"
    5 "You could guess at the unknown members of the Camarilla's Inner Circle"
    :specialties ["Anarchs" "Camarilla" "City government" "Clan (specific)"
                  "Diplomacy" "Media" "National politics" "State/provicial politics"]}
   :science
   {:description "Science is a broad church, covering everything from basic principles of life to the understanding of universal entropy. The laws of science govern the mortal world, and vampires who wish to rule that world study them."
    1 "You dabble in the sciences and understand the principles behind the building blocks of life"
    2 "You can accurately explain the competing scientific theories of the Embrace to another vampire"
    3 "You make an excellent scientific manager; you can run a laboratory, interpret scientific findings, and get up to speed on scientific research in most fields; you can repair scientific equipment"
    4 "You are an expert in your field and in those allied to it"
    5 "Few peers match your understanding, an others come to you for guidance"
    :specialties ["Astronomy" "Biology" "Chemistry" "Demolitions" "Engineering"
                  "Genetics" "Geology" "Mathematics" "Physics"]}
   :technology
   {:description "As a skill, technology acts as something of a moving target; it governs the operation and understanding of \"technical developments most vampires find mystifying\". In 1870, it might have governed steam engines and electricity; these nights, it governs computers and computer systems. Of course, now computers govern almost everything - including steam turbines in power plants and electrical systems in office buildings."
    1 "You know how to upgrade a home PC and keep it protected from viruses"
    2 "You can conceal your IP, operate drones, and fake a digital photograph"
    3 "You can create and unleash your own viruses without fear of detection"
    4 "The Prince might call you personally to manage cybersecurity for their domain"
    5 "On the internet, nobody knows you've a vampire - or that you're there at all"
    :specialties ["Artillery" "Coding" "Computer building" "Data mining" "Hacking"
                  "Networks" "Phones" "Surveillance systems"]}})
