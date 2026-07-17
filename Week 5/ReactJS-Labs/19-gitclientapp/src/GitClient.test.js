import axios from "axios";
import GitClient from "./GitClient";

jest.mock("axios");

describe("Git Client Tests", () => {
  test("should return repository names for techiesyed", async () => {
    const dummyRepos = {
      data: [
        { name: "appcentricsolutions" },
        { name: "ArrayListDemo" },
        { name: "CleanArchitecture" }
      ]
    };

    axios.get.mockResolvedValue(dummyRepos);

    const response = await GitClient.getRepositories("techiesyed");
    
    expect(response.data).toEqual(dummyRepos.data);
    expect(response.data[0].name).toBe("appcentricsolutions");
    expect(axios.get).toHaveBeenCalledWith("https://api.github.com/users/techiesyed/repos");
  });
});