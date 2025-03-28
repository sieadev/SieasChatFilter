<div align='center'>

<h1>Siea's Chat Filter</h1>
<p></p>

<p></p>

<h4> <a href="https://github.com/sieadev/SieasChatFilter/"> Github </a> <span> · </span> <a href="https://github.com/sieadev/SieasChatFilter/issues"> Report Bug </a> <span> · </span> <a href="https://github.com/sieadev/SieasChatFilter/issues"> Request Feature </a> </h4>
<be>

![chatfilterpreview](https://github.com/user-attachments/assets/c25ce690-eb7c-4df7-a1c2-4f2e94ed68d5)

Siea's Chat Filter is a Plugin designed to filter chat messages based on various criteria such as spam, prohibited words, and links. The plugin is highly configurable and allows server administrators to customize the filtering behavior.
</div>

## Features

- **Spam Filter**: Blocks repeated messages sent within a short interval.
- **Word Filter**: Blocks messages containing predefined bad words.
- **Link Filter**: Blocks messages containing links based on a regex pattern.

## Configuration
```yaml
# In this section you can configure all messages that are sent by the
# plugin. You can use color codes (&c, &a, &l, etc.) and placeholders
# (%player%, %message%).
messages:
  chat_message_blocked: '&cYour message was blocked by the chat filter.'

# In this section you can enable or disable the different filters.
filters:
  word-filter: true
  spam-filter: true
  link-filter: false

# This is the regex pattern that is used to detect links in chat messages.
# Only change this if you know what you are doing.
link-pattern: '[A-Za-z0-9-]{2,63}\.[A-Za-z0-9-]{2,63}'

# In this section you can configure the spam filter. The spam filter
# will check if a player is sending the same message multiple times
# in a row. If the player is spamming, the message will be blocked.
spam-cooldown: 30
max-similar-messages: 3

# In this section you can define a list of bad words that you want
# to filter out from chat messages.
bad-words:
  - 'verybadword'
  - 'anotherbadword'
```

## Permissions
- `scf.bypass`: Players with this permission can bypass all filters.
