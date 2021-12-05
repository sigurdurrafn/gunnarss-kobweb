# A website

Created using [Kobweb](https://github.com/varabyte/kobweb)

## Getting Started

First, run the development server by typing the following command in a terminal at this project's root:

```bash
kobweb run
```

Open [http://localhost:8080](http://localhost:8080) with your browser to see the result.

You can use any editor you want for the project, but we recommend using **IntelliJ IDEA Community Edition** downloaded
using the [Toolbox App](https://www.jetbrains.com/toolbox-app/).

Press `Q` (or `CMD/CTRL-D`) in the terminal to gracefully stop the server.


## Exporting the Project

When you are ready to ship, you should shutdown the development server and then export the project using:

```bash
kobweb export
```

When finished, you can run a Kobweb server in production mode:

```bash
kobweb run --env prod
```

You should be able to run this command in the Cloud provider of your choice, at which point, once your Cloud environment
is configured, it will serve your site.