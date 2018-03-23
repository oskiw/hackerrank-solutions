import re


def fun(s):
    # return True if s is a valid email, else return False
    if s.count("@") != 1:
        return False
    username, rest = s.split("@")

    p = re.compile('^[a-z0-9-_]+$', re.IGNORECASE)
    if not p.match(username):
        return False

    if rest.count(".") != 1:
        return False
    website, extension = rest.split(".")

    if not re.match('^[a-z0-9]+$', website, re.IGNORECASE):
        return False

    if len(extension) > 3:
        return False

    return True


def filter_mail(emails):
    return list(filter(fun, emails))

if __name__ == '__main__':
    n = int(input())
    emails = []
    for _ in range(n):
        emails.append(input())

filtered_emails = filter_mail(emails)
filtered_emails.sort()
print(filtered_emails)
